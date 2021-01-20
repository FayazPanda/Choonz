package com.qa.choonz.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 1000)
    private String artwork;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> tracks;

    @NotNull
    @JsonBackReference
    @ManyToOne
    private User user;

    public Playlist(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description, @NotNull @Size(max = 1000) String artwork, @NotNull User user) {
        super();
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.user = user;
    }

    public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description, @NotNull @Size(max = 1000) String artwork, @NotNull User user) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.user = user;
    }

}
