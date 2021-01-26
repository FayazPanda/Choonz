package com.qa.choonz.service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Track_Playlist;
import com.qa.choonz.persistence.repository.Track_PlaylistRepository;
import com.qa.choonz.rest.dto.Track_PlaylistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Track_PlaylistService {

    private final Track_PlaylistRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public Track_PlaylistService(Track_PlaylistRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private Track_PlaylistDTO mapToDTO(Track_Playlist playlist) {
        return this.mapper.map(playlist, Track_PlaylistDTO.class);
    }

    public Track_PlaylistDTO create(Track_Playlist trackPlaylist) {
        Track_Playlist created = this.repo.save(trackPlaylist);
        return this.mapToDTO(created);
    }

    public List<Track_PlaylistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Track_PlaylistDTO read(long id) {
        Track_Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public Track_PlaylistDTO update(Track_Playlist trackPlaylist, long id) {
        Track_Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        toUpdate.setPlaylists(trackPlaylist.getPlaylists());
        toUpdate.setTracks(trackPlaylist.getTracks());
        Track_Playlist updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
