package com.group4.controller;

import com.group4.model.Customer;
import com.group4.model.login.JwtResponse;
import com.group4.model.login.Role;
import com.group4.model.login.User;
import com.group4.service.UserService;
import com.group4.service.iplm.CustomerService;
import com.group4.service.iplm.JwtService;
import com.group4.service.iplm.RoleService;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;


    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<Iterable<User>> showAllUserByAdmin() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            System.out.println("1");
            return new ResponseEntity<>("ero", HttpStatus.BAD_REQUEST);
        }
        User user = customer.getUser();
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>("That username is already in use", HttpStatus.BAD_REQUEST);
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>("Password confirmation is incorrect", HttpStatus.BAD_REQUEST);
        }
        Role roleUser = roleService.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);
        if (customerService.emailIsUse(customer.getEmail())) {
            return new ResponseEntity<>("email has been used ", HttpStatus.BAD_REQUEST);
        }
        if (customerService.phoneIsUse(customer.getPhoneNumber())) {
            return new ResponseEntity<>("The phone number is already in use", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        User myUser = userService.save(user);
        customer.setUser(user);
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        Customer customer = customerService.findCustomerByUserId(currentUser.getId()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), customer.getFullName(), userDetails.getAuthorities()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setEnabled(userOptional.get().isEnabled());
        user.setPassword(userOptional.get().getPassword());
        user.setRoles(userOptional.get().getRoles());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/customers/{userId}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable Long userId) {
        Optional<Customer> customer = customerService.findCustomerByUserId(userId);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/customers/{userId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long userId, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findCustomerByUserId(userId);
        if (customerOptional.isPresent()) {
            Customer customer1 = customerOptional.get();
            customer1.setAddress(customer.getAddress());
            customer1.setFullName(customer.getFullName());
            customer1.setBirthDay(customer.getBirthDay());
            customerService.save(customer1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody User user) {
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>("Password confirmation is incorrect", HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = userService.findById(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            if (!passwordEncoder.matches(user.getUsername(),user1.getPassword())) {
                return new ResponseEntity<>("Old password is incorrect", HttpStatus.BAD_REQUEST);
            }
            user1.setPassword(user.getPassword());
            user1.setConfirmPassword(user.getConfirmPassword());
            userService.save(user1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("error user not found", HttpStatus.NOT_FOUND);
    }
}
