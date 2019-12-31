package com.weebindustry.weebjournal.models;


import java.util.Date;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "votes")
    private int votes;

    @Column(name = "saved")
    private int saved;

    @ManyToOne 
    @JoinColumn(name = "user_id") 
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

}