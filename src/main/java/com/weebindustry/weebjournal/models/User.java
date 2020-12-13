package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NaturalId;
import org.springframework.context.annotation.Configuration;

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
public class User implements Serializable {

    private static final long serialVersionUID = -8544233980065788815L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

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

    @Column(name = "email")
    @NonNull
    @Email

    private String email;

    @Column(name = "created_date")
    private Instant created;

    @Column(name = "enabled")
    private boolean enabled;
}