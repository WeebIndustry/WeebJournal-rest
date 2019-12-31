package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * The warehouse to store every user that exist in weebjournal
 * 
 * 
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"password"})
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

    @OneToMany(mappedBy="users", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Post> posts;
    
}