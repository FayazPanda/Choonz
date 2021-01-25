package com.qa.choonz.service;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

	private final TrackRepository trackRepo;
    private final AlbumRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public AlbumService(AlbumRepository repo,  TrackRepository trackRepo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.trackRepo = trackRepo;
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
public AlbumDTO deleteTrack(Album album, long trackID) {

	// Find the album in DB
	Album toUpdate = this.repo.findById(album.getId()).orElseThrow(PlaylistNotFoundException::new);
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

public AlbumDTO addTrack(Album album, long trackID) {

	// Find the playlist in DB
	Album toUpdate = this.repo.findById(album.getId()).orElseThrow(PlaylistNotFoundException::new);
	List<Track> tracks = toUpdate.getTracks();
	

	Track toAdd = new Track();
	toAdd.setId(trackID);
	
	tracks.add(toAdd);
	toUpdate.setTracks(tracks);

	update(toUpdate, toUpdate.getId());
	return this.mapToDTO(toUpdate);
	
}

public AlbumDTO clearTracks(Album album) {

	// Find the playlist in DB
	Album toUpdate = this.repo.findById(album.getId()).orElseThrow(PlaylistNotFoundException::new);
	List<Track> tracks = toUpdate.getTracks();
	
	
	for(Track track : tracks) {
		System.out.println("HI" + track.getId());
		this.trackRepo.deleteById(track.getId());
		
	}
	
	//tracks.removeAll(tracks);
	//toUpdate.setTracks(tracks);

	//update(toUpdate, toUpdate.getId());
	return this.mapToDTO(toUpdate);
	
}
    public boolean delete(long id) {
       	//Album toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
    	
       	//clearTracks(toUpdate);
       	
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
       //	return false;
    }

}
