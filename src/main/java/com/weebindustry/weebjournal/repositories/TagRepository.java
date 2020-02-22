package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    
}   