package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Album;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GenreDTO {

    private long id;
    private String name;
    private String description;
    private List<Album> albums;

}
