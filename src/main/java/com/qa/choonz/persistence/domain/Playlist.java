package com.qa.choonz.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "playlists",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Track_Playlist> trackPlaylists;

    @NotNull
    @ManyToOne
    private User user;

    public Playlist(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description, @NotNull @Size(max = 1000) String artwork) {
        super();
        this.name = name;
        this.description = description;
        this.artwork = artwork;
    }

    public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description, @NotNull @Size(max = 1000) String artwork) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
    }
}
