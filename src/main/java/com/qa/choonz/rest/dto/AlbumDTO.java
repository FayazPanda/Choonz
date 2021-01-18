package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlbumDTO {

    private long id;
    private String name;
    private List<Track> tracks;
    private Artist artist;
    private Genre genre;
    private String cover;

}
