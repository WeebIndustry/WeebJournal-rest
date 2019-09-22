package com.weebindustry.weebjournal.models;

import javax.persistence.*;

import lombok.Data;

import java.util.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "role_name", nullable = false)
    private String role_name;
}   