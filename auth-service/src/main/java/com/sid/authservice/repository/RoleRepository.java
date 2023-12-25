package com.sid.authservice.repository;

import com.sid.authservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleById(Long id);
}
