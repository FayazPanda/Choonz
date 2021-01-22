package com.qa.choonz.rest.dto;

import java.util.List;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDTO {

    private Long id;
    private String name;
    private int duration;
    private String lyrics;
    private Album album;

}
