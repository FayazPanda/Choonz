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
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 500)
    @Column(unique = true)
    private String description;

    @NotNull
    @Size(max = 1000)
    @Column(unique = true)
    private String artwork;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> tracks;

    @NotNull
    @JsonBackReference
    @ManyToOne
    private User user;
    
    public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
                    @NotNull @Size(max = 1000) String artwork, List<Track> tracks) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
    }

	public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			@NotNull @Size(max = 1000) String artwork, List<Track> tracks, @NotNull User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.tracks = tracks;
		this.user = user;
	}

	public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			@NotNull @Size(max = 1000) String artwork, @NotNull User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.user = user;
	}

	
}
