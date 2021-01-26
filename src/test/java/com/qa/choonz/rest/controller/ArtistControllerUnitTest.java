package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase
public class ArtistControllerUnitTest {

    private final Artist TARTIST1 = new Artist("Alice");
    private final Artist TARTIST2 = new Artist("Boris");
    private final Artist TARTIST3 = new Artist("Christopher");
    private final Artist TARTIST4 = new Artist("Daniel");
    private final List<Artist> ARTISTS = List.of(TARTIST1, TARTIST2, TARTIST3, TARTIST4);
    @Autowired
    private ArtistController controller;
    @MockBean
    private ArtistService service;
    @Autowired
    private ModelMapper mapper;

    private ArtistDTO maptoDto(Artist tasks) {
        return this.mapper.map(tasks, ArtistDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TARTIST1)).thenReturn(this.maptoDto(TARTIST1));
        assertThat(new ResponseEntity<ArtistDTO>(this.maptoDto(TARTIST1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TARTIST1));
        verify(this.service, atLeastOnce()).create(TARTIST1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TARTIST1.getId())).thenReturn(this.maptoDto(TARTIST1));
        assertThat(new ResponseEntity<ArtistDTO>(this.maptoDto(TARTIST1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TARTIST1.getId()));
        verify(this.service, atLeastOnce()).read(TARTIST1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<ArtistDTO> dtos = ARTISTS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TARTIST1, TARTIST1.getId())).thenReturn(this.maptoDto(TARTIST1));
        assertThat(new ResponseEntity<ArtistDTO>(this.maptoDto(TARTIST1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TARTIST1, TARTIST1.getId()));
        verify(this.service, atLeastOnce()).update(TARTIST1, TARTIST1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TARTIST1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TARTIST1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TARTIST1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TARTIST1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TARTIST1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TARTIST1.getId());
    }

}
