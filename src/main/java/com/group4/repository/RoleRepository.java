package com.group4.repository;

import com.group4.model.login.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    Role findByName(String roleName);
}
