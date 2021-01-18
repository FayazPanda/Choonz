package com.qa.choonz.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    @Column(unique = true)
    private String description;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Album> albums;

    public Genre(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 250) String description,
                 List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.albums = albums;
    }
}
