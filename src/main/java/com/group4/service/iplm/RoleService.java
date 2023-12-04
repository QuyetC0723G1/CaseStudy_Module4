package com.group4.service.iplm;

import com.group4.model.login.Role;
import com.group4.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}
