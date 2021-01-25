package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @JsonBackReference
    @ManyToOne(targetEntity = Album.class)
    private Album album;

    @JsonManagedReference
    @OneToMany(mappedBy = "tracks", cascade = CascadeType.ALL)
    private List<Track_Playlist> trackPlaylists;

    // in seconds
    private int duration;

    private String lyrics;

	public Track(@NotNull @Size(max = 100) String name, int duration, String lyrics) {
        super();
	    this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
	}

	public Track(long id, @NotNull @Size(max = 100) String name, int duration, String lyrics) {
        super();
	    this.id = id;
		this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
	}

}
