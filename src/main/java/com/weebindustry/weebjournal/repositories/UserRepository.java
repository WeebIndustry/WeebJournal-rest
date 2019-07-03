package com.weebindustry.weebjournal.repositories;


import java.util.*; 

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select * from users u where u.username = :name and u.password = password")
    Optional<User> loginValidation(@Param("name") String username, @Param("pass") String password);
}