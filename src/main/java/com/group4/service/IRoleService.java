package com.group4.service;

import com.group4.model.login.Role;

public interface IRoleService{
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}
