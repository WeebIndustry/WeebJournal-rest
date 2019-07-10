package com.weebindustry.weebjournal.repositories;


import java.util.*; 

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value = "select * from users username = ?1 and password = ?2", nativeQuery = true)
    Optional<User> loginValidation(String username, String password);
}