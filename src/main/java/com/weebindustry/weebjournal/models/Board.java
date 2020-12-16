package com.weebindustry.weebjournal.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "boards")
public class Board implements Serializable {
    private static final long serialVersionUID = 8994425524708923780L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "board_id")
    private Long boardId;

    @NotBlank(message = "Community name is required")
    @Column(name = "board_name")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "board_description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
