package com.qa.choonz.service;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private final AlbumRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public AlbumService(AlbumRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }

    public AlbumDTO create(Album album) {
        return this.mapToDTO(this.repo.save(album));
    }

    public List<AlbumDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AlbumDTO read(Long id) {
        return this.mapToDTO(this.repo.findById(id).orElseThrow());
    }

    public AlbumDTO update(Album album, long id) {
        Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        toUpdate.setName(toUpdate.getName());
        toUpdate.setTracks(toUpdate.getTracks());
        toUpdate.setArtist(toUpdate.getArtist());
        toUpdate.setCover(toUpdate.getCover());
        Album updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
