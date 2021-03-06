package com.qa.choonz.persistence.domain;

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
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @JsonManagedReference(value = "artistAlbum")
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
