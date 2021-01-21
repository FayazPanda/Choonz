package com.qa.choonz.persistence.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
