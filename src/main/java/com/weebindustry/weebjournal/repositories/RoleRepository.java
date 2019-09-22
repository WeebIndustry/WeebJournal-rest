package com.weebindustry.weebjournal.repositories;


import java.util.Optional;

import com.weebindustry.weebjournal.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findById(int id);

    
}