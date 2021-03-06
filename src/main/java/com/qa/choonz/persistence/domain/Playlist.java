package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "track_Playlist")
    private List<Track_Playlist> trackPlaylists = new ArrayList<>();


    @NotNull
    @JsonBackReference(value = "playlist_user")
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
