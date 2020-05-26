package com.dangth.bhxh.repository;

import com.dangth.bhxh.model.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}