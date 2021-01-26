package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Track_PlaylistDTO {

    private long id;
    private List<Track> tracks;
    private List<Playlist> playlists;

}
