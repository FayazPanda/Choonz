package com.qa.choonz.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
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
public class TrackControllerIntergrationTest {

    private final String URI = "/tracks";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private TrackDTO mapToDTO(Track tracks) {
        return this.mapper.map(tracks, TrackDTO.class);
    }

    void createEntity(Track track) throws Exception {
        TrackDTO testDTO = mapToDTO(track);
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        this.mvc.perform(request);
    }

    @Test
    void createTest() throws Exception {
        TrackDTO testDTO = mapToDTO(new Track("Panda", 456, "The life of a panda is a simple one"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        TrackDTO testSavedDTO = mapToDTO(new Track(22L, "Panda", 456, "The life of a panda is a simple one"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        createEntity(new Track("Panda", 456, "The life of a panda is a simple one"));

        RequestBuilder request = get(URI + "/read/22");
        ResultMatcher checkStatus = status().isOk();

        TrackDTO testSavedDTO = mapToDTO(new Track(22L, "Panda", 456, "The life of a panda is a simple one"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        for (int i = 1; i < 22; i++) {
            this.mvc.perform(delete(URI + "/delete/" + i));
        }

        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(mapToDTO(new Track(22L, "Panda", 456, "The life of a panda is a simple one")));
        tracks.add(mapToDTO(new Track(23L, "Dog", 456, "The life of a dog is a simple one")));
        tracks.add(mapToDTO(new Track(24L, "Cat", 456, "The life of a cat is a simple one")));
        tracks.add(mapToDTO(new Track(25L, "Mamoth", 456, "The life of a mamoth is not a simple one")));
        tracks.add(mapToDTO(new Track(26L, "Panther", 456, "The life of a panther is a simple one")));

        createEntity(new Track("Panda", 456, "The life of a panda is a simple one"));
        createEntity(new Track("Dog", 456, "The life of a dog is a simple one"));
        createEntity(new Track("Cat", 456, "The life of a cat is a simple one"));
        createEntity(new Track("Mamoth", 456, "The life of a mamoth is not a simple one"));
        createEntity(new Track("Panther", 456, "The life of a panther is a simple one"));

        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(tracks);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {

        createEntity(new Track("Panda", 456, "The life of a panda is a simple one"));

        TrackDTO testDTO = mapToDTO(new Track("Dog", 456, "This was a Panda, Now this is Dog"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/22").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        TrackDTO testSavedDTO = mapToDTO(new Track(22L, "Dog", 456, "This was a Panda, Now this is Dog"));
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
