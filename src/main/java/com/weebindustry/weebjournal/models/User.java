package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.weebindustry.weebjournal.models.audit.DateAudit;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
@Builder
public class User extends DateAudit implements Serializable {

    private static final long serialVersionUID = -8544233980065788815L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NonNull
    @Size(min = 3, max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @NonNull
    @Column(name = "password")
    @JsonIgnore
    @Size(min = 6, max = 100)
    private String password;

    @NaturalId
    @Column(name = "email")
    @NonNull
    @Email
    private String email;

    @Column(name = "biography", nullable = true)
    private String biography;

    @Column(name = "displayname", nullable = true)
    @Size(min = 3, max = 50)
    private String displayname;

    @Column(name = "date_of_birth", nullable = true)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public static User of(String username, String password, String email) throws ParseException {
        return User.builder().username(username).password(password).email(email).build();
    }
}