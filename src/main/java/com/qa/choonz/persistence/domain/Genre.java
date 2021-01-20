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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 250)
    @Column(unique = true)
    private String description;
    
    @JsonManagedReference(value = "albumGenre")
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Album> albums = new ArrayList<>();

    public Genre( @NotNull @Size(max = 100) String name, @NotNull @Size(max = 250) String description) {
        super();
        this.name = name;
        this.description = description;

    }

    public Genre(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 250) String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;

    }
}
