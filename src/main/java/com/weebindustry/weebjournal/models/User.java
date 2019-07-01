package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
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

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "joined_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "biography", nullable = true)
    private String biography;

    @Column(name = "displayname", nullable = true)
    private String displayname;

    @Column(name="date_of_birth", nullable = true)
    private Date dateOfBirth;
    
}