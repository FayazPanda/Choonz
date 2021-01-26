package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;
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
public class GenreControllerUnitTest {

    private final Genre TGENRE1 = new Genre("Pop", "coca cola, pepsi, fanta");
    private final Genre TGENRE2 = new Genre("Rock", "sedimentary, metamorphic, igneous");
    private final Genre TGENRE3 = new Genre("Punk", "cyberpunk 2077");
    private final Genre TGENRE4 = new Genre("Rap", "bandage");
    private final Genre TGENRE5 = new Genre("Metal", "steel, alloy,copper");
    private final List<Genre> GENRES = List.of(TGENRE1, TGENRE2, TGENRE3, TGENRE4, TGENRE5);
    @Autowired
    private GenreController controller;
    @MockBean
    private GenreService service;
    @Autowired
    private ModelMapper mapper;

    private GenreDTO maptoDto(Genre tasks) {
        return this.mapper.map(tasks, GenreDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TGENRE1)).thenReturn(this.maptoDto(TGENRE1));
        assertThat(new ResponseEntity<GenreDTO>(this.maptoDto(TGENRE1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TGENRE1));
        verify(this.service, atLeastOnce()).create(TGENRE1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TGENRE1.getId())).thenReturn(this.maptoDto(TGENRE1));
        assertThat(new ResponseEntity<GenreDTO>(this.maptoDto(TGENRE1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TGENRE1.getId()));
        verify(this.service, atLeastOnce()).read(TGENRE1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<GenreDTO> dtos = GENRES.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TGENRE1, TGENRE1.getId())).thenReturn(this.maptoDto(TGENRE1));
        assertThat(new ResponseEntity<GenreDTO>(this.maptoDto(TGENRE1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TGENRE1, TGENRE1.getId()));
        verify(this.service, atLeastOnce()).update(TGENRE1, TGENRE1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TGENRE1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TGENRE1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TGENRE1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TGENRE1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TGENRE1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TGENRE1.getId());
    }

}
