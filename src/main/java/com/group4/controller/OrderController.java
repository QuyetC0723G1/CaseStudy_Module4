package com.group4.controller;

import com.group4.model.Order;
import com.group4.model.OrderDetail;
import com.group4.model.OrderRequest;
import com.group4.model.Product;
import com.group4.service.iplm.CustomerService;
import com.group4.service.iplm.OrderDetailService;
import com.group4.service.iplm.OrderService;
import com.group4.service.iplm.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderDetailService orderDetailService;
    @PostMapping("/checkout")
    public ResponseEntity<Iterable<Product>> getAll(@RequestBody List<Long> ids){
        return new ResponseEntity<>(productService.findAllByIdIn(ids), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Order> save(@RequestBody OrderRequest request){
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setCustomer(customerService.findOneById(1L).get());
        order.setTotalMoney(request.getTotal());
        order.setStatus(0);
        orderService.save(order);
        for(int i=0;i<request.getData().size();i++){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantityPick(request.getData().get(i).getQuantity());
            orderDetail.setProduct(productService.findOneById(request.getData().get(i).getId()).get());
            orderDetailService.save(orderDetail);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
