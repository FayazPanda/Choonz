package com.qa.choonz.service;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    public UserDTO create(User user) {
        // Create user with un-hashed password
        User userNoPass = user;

        //Create hashed password
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        // Set hashed password
        user.setPassword(pw_hash);

        User created = this.repo.save(user);
        return this.mapToDTO(created);
    }

    public List<UserDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO read(long id) {
        User found = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        return this.mapToDTO(found);
    }
    public UserDTO find(String username) throws UserNotFoundException {
    	List<UserDTO> all = read();
    	User found = new User();
    	for(UserDTO record : all) {
    		if(record.getUsername().equals(username)) {
    			found = this.repo.findById(record.getId()).orElseThrow(UserNotFoundException::new);
    		}    		
    	}
       // User found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        return this.mapToDTO(found);
    }
    // Takes in user with password, returns true if password is correct, false if not
    public Boolean login(User user) {
    	// Get the user from system to check against
        UserDTO found = find(user.getUsername());
        Boolean foundBool = false;

        // Checks inputted password against system
        if (BCrypt.checkpw(user.getPassword(), found.getPassword())) {
            System.out.println("It matches");
        	foundBool = true;
        }
        else {
            System.out.println("It does not match");
            foundBool = false;
        }
        // Returns true if password matches, false if not
        return foundBool;
    }

    public UserDTO update(User user, long id) {
        User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        toUpdate.setUsername(user.getUsername());
        toUpdate.setPassword(user.getPassword());
        User updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
