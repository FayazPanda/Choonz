package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@CrossOrigin
public class AlbumController {

    private final AlbumService service;

    @Autowired
    public AlbumController(AlbumService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<AlbumDTO> create(@RequestBody Album album) {
        return new ResponseEntity<AlbumDTO>(this.service.create(album), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<AlbumDTO>> read() {
        return new ResponseEntity<List<AlbumDTO>>(this.service.readAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AlbumDTO> read(@PathVariable long id) {
        return new ResponseEntity<AlbumDTO>(this.service.readOne(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<AlbumDTO> update(@RequestBody Album album, @PathVariable long id) {
        return new ResponseEntity<AlbumDTO>(this.service.update(album, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<AlbumDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<AlbumDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<AlbumDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
