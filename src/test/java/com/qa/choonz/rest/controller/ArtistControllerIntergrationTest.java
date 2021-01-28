package com.qa.choonz.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql(scripts = {"classpath:schema.sql",
        "classpath:data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ArtistControllerIntergrationTest {

    private final String URI = "/artists";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private ArtistDTO mapToDTO(Artist artist) {
        return this.mapper.map(artist, ArtistDTO.class);
    }

    void createEntity(Artist artist) throws Exception {
        ArtistDTO testDTO = mapToDTO(artist);
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        this.mvc.perform(request);
    }


    @Test
    void createTest() throws Exception {
        ArtistDTO testDTO = mapToDTO(new Artist("Panda"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        ArtistDTO testSavedDTO = mapToDTO(new Artist(5L, "Panda"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        createEntity(new Artist("Panda"));

        RequestBuilder request = get(URI + "/read/5");
        ResultMatcher checkStatus = status().isOk();

        ArtistDTO testSavedDTO = mapToDTO(new Artist(5L, "Panda"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        for (int i = 1; i < 5; i++) {
            this.mvc.perform(delete(URI + "/delete/" + i));
        }

        List<ArtistDTO> tracks = new ArrayList<>();
        tracks.add(mapToDTO(new Artist(5L, "Panda")));
        tracks.add(mapToDTO(new Artist(6L, "Dog")));
        tracks.add(mapToDTO(new Artist(7L, "Cat")));
        tracks.add(mapToDTO(new Artist(8L, "Mamoth")));
        tracks.add(mapToDTO(new Artist(9L, "Panther")));

        createEntity(new Artist("Panda"));
        createEntity(new Artist("Dog"));
        createEntity(new Artist("Cat"));
        createEntity(new Artist("Mamoth"));
        createEntity(new Artist("Panther"));

        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(tracks);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {

        createEntity(new Artist("Panda"));

        ArtistDTO testDTO = mapToDTO(new Artist("Dog"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/5").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        ArtistDTO testSavedDTO = mapToDTO(new Artist(5L, "Dog"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void deleteTest() throws Exception {

        RequestBuilder request = delete(URI + "/delete/1");
        ResultMatcher checkStatus = status().isNoContent();

        this.mvc.perform(request).andExpect(checkStatus);

    }

}
