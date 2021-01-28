package com.qa.choonz.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;
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
public class GenreControllerIntergrationTest {

    private final String URI = "/genres";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private GenreDTO mapToDTO(Genre genre) {
        return this.mapper.map(genre, GenreDTO.class);
    }

    void createEntity(Genre genre) throws Exception {
        GenreDTO testDTO = mapToDTO(genre);
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        this.mvc.perform(request);
    }

    @Test
    void createTest() throws Exception {
        GenreDTO testDTO = mapToDTO(new Genre("Panda", "Bamboo, Crayons"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        GenreDTO testSavedDTO = mapToDTO(new Genre(6L, "Panda", "Bamboo, Crayons"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        createEntity(new Genre("Panda", "Bamboo, Crayons"));

        RequestBuilder request = get(URI + "/read/6");
        ResultMatcher checkStatus = status().isOk();

        GenreDTO testSavedDTO = mapToDTO(new Genre(6L, "Panda", "Bamboo, Crayons"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        for (int i = 1; i < 6; i++) {
            this.mvc.perform(delete(URI + "/delete/" + i));
        }

        List<GenreDTO> genres = new ArrayList<>();
        genres.add(mapToDTO(new Genre(6L, "Panda", "Bamboo, Crayons")));
        genres.add(mapToDTO(new Genre(7L, "Dog", "Bones, Treats, Sit")));
        genres.add(mapToDTO(new Genre(8L, "Cat", "Whiskers, Meow")));
        genres.add(mapToDTO(new Genre(9L, "Mamoth", "Tusks")));
        genres.add(mapToDTO(new Genre(10L, "Panther", "Whiskers, Growl")));

        createEntity(new Genre("Panda", "Bamboo, Crayons"));
        createEntity(new Genre("Dog", "Bones, Treats, Sit"));
        createEntity(new Genre("Cat", "Whiskers, Meow"));
        createEntity(new Genre("Mamoth", "Tusks"));
        createEntity(new Genre("Panther", "Whiskers, Growl"));

        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(genres);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {

        createEntity(new Genre("Panda", "Bamboo, Crayons"));

        GenreDTO testDTO = mapToDTO(new Genre("Dog", "Bones, Treats, Sit"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/6").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        GenreDTO testSavedDTO = mapToDTO(new Genre(6L, "Dog", "Bones, Treats, Sit"));
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
