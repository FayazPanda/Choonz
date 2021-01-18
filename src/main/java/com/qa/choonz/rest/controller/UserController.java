package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.TrackService;
import com.qa.choonz.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        return new ResponseEntity<UserDTO>(this.service.create(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
    	
    	return new ResponseEntity<Boolean>(this.service.login(user), HttpStatus.OK);
    	
    } 
    @GetMapping("/read")
    public ResponseEntity<List<UserDTO>> read() {
        return new ResponseEntity<List<UserDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable long id) {
        return new ResponseEntity<UserDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable long id) {
        return new ResponseEntity<UserDTO>(this.service.update(user, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
