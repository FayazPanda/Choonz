package com.qa.choonz.persistence.repository;

import com.qa.choonz.persistence.domain.Track_Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Track_PlaylistRepository extends JpaRepository<Track_Playlist, Long> {

    @Query(value = "SELECT * FROM track_playlist WHERE playlists_id =?1", nativeQuery = true)
    List<Track_Playlist> findTracks(Long id);
}
