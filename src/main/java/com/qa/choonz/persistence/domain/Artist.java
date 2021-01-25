package com.qa.choonz.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;
    
    @JsonManagedReference(value = "artistAlbum")
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Album> albums = new ArrayList<>();

    public Artist(@NotNull @Size(max = 100) String name) {
        super();
        this.name = name;
    }

    public Artist(long id, @NotNull @Size(max = 100) String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
