package com.weebindustry.weebjournal.models;

import java.time.Instant;
import java.util.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.*;

import lombok.*;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 7441073095469088061L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title")
    @NotBlank(message = "Post Title cannot be empty no Null")
    private String title;

    @Column(name = "url")
    @Nullable
    private String url;

    @Column(name = "post_content")
    @Nullable
    @Lob
    private String content;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId", referencedColumnName = "board_id")
    private Board board;
}