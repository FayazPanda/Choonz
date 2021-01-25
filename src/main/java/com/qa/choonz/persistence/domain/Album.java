package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Track> tracks = new ArrayList<>();

    @JsonBackReference(value = "artistAlbum")
    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;
    
    @JsonBackReference(value = "albumGenre")
    @ManyToOne
    private Genre genre;

    private String cover;


    public Album(@NotNull @Size(max = 100) String name, String cover) {
        super();
        this.name = name;
        this.cover = cover;
    }

    public Album(long id, @NotNull @Size(max = 100) String name, String cover) {
        super();
        this.id = id;
        this.name = name;
        this.cover = cover;
    }
}
