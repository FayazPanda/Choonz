package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;
import com.sipios.springsearch.anotation.SearchSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
@CrossOrigin
public class ArtistController {
	
	private final ArtistRepository artistRepository;
    private final ArtistService service;

    @Autowired
    public ArtistController(ArtistService service, ArtistRepository artistRepository) {
        super();
        this.artistRepository = artistRepository;
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> create(@RequestBody Artist artist) {
        return new ResponseEntity<ArtistDTO>(this.service.create(artist), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<ArtistDTO>> read() {
        return new ResponseEntity<List<ArtistDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<ArtistDTO> read(@PathVariable long id) {
        return new ResponseEntity<ArtistDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ArtistDTO> update(@RequestBody Artist artist, @PathVariable long id) {
        return new ResponseEntity<ArtistDTO>(this.service.update(artist, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ArtistDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<ArtistDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<ArtistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Artist>> searchForArtists(@SearchSpec Specification<Artist> specs) {
        return new ResponseEntity<>(artistRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

}
