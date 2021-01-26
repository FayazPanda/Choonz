package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracks")
@CrossOrigin
public class TrackController {

    private final TrackRepository trackRepository;
    private final TrackService service;

    @Autowired
    public TrackController(TrackService service, TrackRepository trackRepository) {
        super();
        this.trackRepository = trackRepository;
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TrackDTO> create(@RequestBody Track track) {
        return new ResponseEntity<TrackDTO>(this.service.create(track), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<TrackDTO>> read() {
        return new ResponseEntity<List<TrackDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<TrackDTO> read(@PathVariable long id) {
        return new ResponseEntity<TrackDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TrackDTO> update(@RequestBody Track track, @PathVariable long id) {
        return new ResponseEntity<TrackDTO>(this.service.update(track, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<TrackDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<TrackDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<TrackDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Track>> searchForTracks(@SearchSpec Specification<Track> specs) {
        return new ResponseEntity<>(trackRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }
}
