package com.savelife.project.repositories;

import com.savelife.project.entities.Role;
import com.savelife.project.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(Roles roles);
}
