package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistService service;

    @Autowired
    public PlaylistController(PlaylistService service, PlaylistRepository playlistRepository) {
        super();
        this.playlistRepository = playlistRepository;
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody Playlist playlist) {
        return new ResponseEntity<PlaylistDTO>(this.service.create(playlist), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<PlaylistDTO>> read() {
        return new ResponseEntity<List<PlaylistDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PlaylistDTO> read(@PathVariable long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.read(id), HttpStatus.OK);
    }

    @GetMapping("/readUser/{id}")
    public ResponseEntity<PlaylistDTO> readUser(@PathVariable Long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlaylistDTO> update(@RequestBody Playlist playlist, @PathVariable long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.update(playlist, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlaylistDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<PlaylistDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<PlaylistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Playlist>> searchForPlaylists(@SearchSpec Specification<Playlist> specs) {
        return new ResponseEntity<>(playlistRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

}
