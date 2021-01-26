package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Track_Playlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotNull
    @JsonBackReference(value = "playlist_tracks")
    @JoinColumn(name = "tracks_id")
    Track tracks;

    @ManyToOne
    @NotNull
    @JsonBackReference(value = "track_Playlist")
    @JoinColumn(name = "playlists_id")
    Playlist playlists;


    public Track_Playlist(@NotNull Track tracks, @NotNull Playlist playlists) {
        super();
        this.tracks = tracks;
        this.playlists = playlists;
    }

    public Track_Playlist(long id, @NotNull Track tracks, @NotNull Playlist playlists) {
        super();
        this.id = id;
        this.tracks = tracks;
        this.playlists = playlists;
    }
}
