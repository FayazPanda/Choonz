package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Playlist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private List<Playlist> playlists;
    private int permissions;
}
