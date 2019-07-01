package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    
}