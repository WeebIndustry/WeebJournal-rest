package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = -8544233980065788815L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "joined_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @Column(name = "email")
    private String email;

    @Column(name = "biography")
    private String biography;

    @Column(name = "displayname")
    private String displayname;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();
    
}