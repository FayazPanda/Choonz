package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Track_Playlist;
import com.qa.choonz.rest.dto.Track_PlaylistDTO;
import com.qa.choonz.service.Track_PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trackPlaylist")
@CrossOrigin
public class Track_PlaylistController {

    private final Track_PlaylistService service;

    @Autowired
    public Track_PlaylistController(Track_PlaylistService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Track_PlaylistDTO> create(@RequestBody Track_Playlist trackPlaylist) {
        return new ResponseEntity<Track_PlaylistDTO>(this.service.create(trackPlaylist), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Track_PlaylistDTO>> read() {
        return new ResponseEntity<List<Track_PlaylistDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Track_PlaylistDTO> read(@PathVariable long id) {
        return new ResponseEntity<Track_PlaylistDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Track_PlaylistDTO> update(@RequestBody Track_Playlist trackPlaylist, @PathVariable long id) {
        return new ResponseEntity<Track_PlaylistDTO>(this.service.update(trackPlaylist, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Track_PlaylistDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<Track_PlaylistDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<Track_PlaylistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("findTracks/{id}")
    public ResponseEntity<List<Track_PlaylistDTO>> findByName(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findTracks(id));
    }
}
