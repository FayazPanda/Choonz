package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Track_Playlist;
import com.qa.choonz.persistence.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlaylistDTO {

    private long id;
    private String name;
    private String description;
    private String artwork;
    private List<Track_Playlist> trackPlaylists;
    private User user;
}
