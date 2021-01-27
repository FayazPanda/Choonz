package com.qa.choonz.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.AlbumDTO;
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
public class AlbumControllerIntergrationTest {

    private final String URI = "/albums";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }

    void createEntity(Album album) throws Exception {
        AlbumDTO testDTO = mapToDTO(album);
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        this.mvc.perform(request);
    }

    @Test
    void createTest() throws Exception {
        AlbumDTO testDTO = mapToDTO(new Album("Panda","cover"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        AlbumDTO testSavedDTO = mapToDTO(new Album(6L,"Panda","cover"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        createEntity(new Album("Panda","cover"));

        RequestBuilder request = get(URI + "/read/6");
        ResultMatcher checkStatus = status().isOk();

        AlbumDTO testSavedDTO = mapToDTO(new Album(6L,"Panda","cover"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        for (int i=1;i < 6;i++){
            this.mvc.perform(delete(URI + "/delete/"+i));
        }

        List<AlbumDTO> tracks = new ArrayList<>();
        tracks.add(mapToDTO(new Album(6L,"Panda","cover")));
        tracks.add(mapToDTO(new Album(7L,"Dog","cover2")));
        tracks.add(mapToDTO(new Album(8L,"Cat","cover3")));
        tracks.add(mapToDTO(new Album(9L,"Mamoth","cover4")));
        tracks.add(mapToDTO(new Album(10L,"Panther","cover5")));

        createEntity(new Album("Panda","cover"));
        createEntity(new Album("Dog","cover2"));
        createEntity(new Album("Cat","cover3"));
        createEntity(new Album("Mamoth","cover4"));
        createEntity(new Album("Panther","cover5"));

        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(tracks);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {

        createEntity(new Album("Panda","cover"));

        AlbumDTO testDTO = mapToDTO(new Album("Dog","cover2"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/6").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        AlbumDTO testSavedDTO = mapToDTO(new Album(6L,"Dog","cover2"));
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
