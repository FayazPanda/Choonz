package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track_PlaylistDTO {

    private long id;
    private Track tracks;
    private Playlist playlists;

}
