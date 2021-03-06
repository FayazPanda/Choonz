package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    // Id
    // username
    // password

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Column(columnDefinition = "integer default 0")
    private int permissions;

    @JsonManagedReference(value = "playlist_user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Playlist> playlists;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;

    }

    public User(String username, String password, int permissions) {
        super();
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }

    public User(long id, String username, String password, int permissions) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }


}
