package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Album;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ArtistDTO {

    private long id;
    private String name;
    private List<Album> albums;

    public ArtistDTO(long id, String name, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.albums = albums;
    }
}
