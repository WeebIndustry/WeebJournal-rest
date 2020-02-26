package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}