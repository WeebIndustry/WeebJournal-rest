package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.Role;
import com.weebindustry.weebjournal.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}