package com.weebindustry.weebjournal.repositories;

import java.util.List;
import java.util.Optional;

import com.weebindustry.weebjournal.models.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    Page<Post> findByUserId(Long userId, Pageable pageable);

    Optional<Post> findByIdAndUserId(Long id, Long userId);

    @Query("from Post p where p.user.id = :userId")
    List<Post> findByUserIdWithoutPageable(@Param("userId") Long userId);

    @Query("from Post p where p.user.username = :username")
    Page<Post> findByUserName(@Param("username") String username, Pageable pageable);

    @Query("from Post p where p.user.username = :username")
    List<Post> findByUserNameWithoutPageable(@Param("username") String username);

    @Query("select count(p.id) from Post p where p.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
}