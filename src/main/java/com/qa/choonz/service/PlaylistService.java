package com.qa.choonz.service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {

    private final PlaylistRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public PlaylistService(PlaylistRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.mapper.map(playlist, PlaylistDTO.class);
    }

    public PlaylistDTO create(Playlist playlist) {
        Playlist created = this.repo.save(playlist);
        return this.mapToDTO(created);
    }

    public List<PlaylistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlaylistDTO read(long id) {
        Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public PlaylistDTO readUser(Long id) {
        Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }
    public PlaylistDTO update(Playlist playlist, long id) {
        Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        toUpdate.setName(playlist.getName());
        toUpdate.setDescription(playlist.getDescription());
        toUpdate.setArtwork(playlist.getArtwork());
        toUpdate.setTracks(playlist.getTracks());
        Playlist updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    
    public PlaylistDTO deleteTrack(Playlist playlist, long trackID) {

    	// Find the playlist in DB
    	Playlist toUpdate = this.repo.findById(playlist.getId()).orElseThrow(PlaylistNotFoundException::new);
    	List<Track> tracks = toUpdate.getTracks();
    	
 
    	Track toRemove = new Track();
    	toRemove.setId(trackID);
    	
    	for(Track track : tracks) {
    		if(track.getId()==trackID) {
    			toRemove = track;
    			
    		}
    	}
    	tracks.remove(toRemove);
    	toUpdate.setTracks(tracks);

		update(toUpdate, toUpdate.getId());
		return this.mapToDTO(toUpdate);
    	
    }
    
    public PlaylistDTO addTrack(Playlist playlist, long trackID) {
   
    	// Find the playlist in DB
    	Playlist toUpdate = this.repo.findById(playlist.getId()).orElseThrow(PlaylistNotFoundException::new);
    	List<Track> tracks = toUpdate.getTracks();
    	
 
    	Track toAdd = new Track();
    	toAdd.setId(trackID);
    	
    	tracks.add(toAdd);
    	toUpdate.setTracks(tracks);

		update(toUpdate, toUpdate.getId());
		return this.mapToDTO(toUpdate);
    	
    }
    
    public PlaylistDTO clearTracks(Playlist playlist) {

    	// Find the playlist in DB
    	Playlist toUpdate = this.repo.findById(playlist.getId()).orElseThrow(PlaylistNotFoundException::new);
    	
    	// Gets tracks
    	List<Track> tracks = toUpdate.getTracks();

    	tracks.clear();
    	// Adds empty track list to playlist object
    	toUpdate.setTracks(tracks);

    	// Updates it
		update(toUpdate, toUpdate.getId());
		return this.mapToDTO(toUpdate);
    	
    }
    
    
    public boolean delete(long id) {
    	// Gets playlist
    	Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
    	
    	// Clears the tracks
    	clearTracks(toUpdate);
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
