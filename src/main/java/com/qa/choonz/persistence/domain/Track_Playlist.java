package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


}
