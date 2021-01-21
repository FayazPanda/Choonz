package com.qa.choonz.persistence.repository;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {

}
