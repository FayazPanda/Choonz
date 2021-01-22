package com.qa.choonz.rest.dto;

import java.util.List;

import com.qa.choonz.persistence.domain.Playlist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private List<Playlist> playlists;
}
