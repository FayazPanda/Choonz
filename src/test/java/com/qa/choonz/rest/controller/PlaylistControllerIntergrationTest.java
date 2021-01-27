package com.qa.choonz.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
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
public class PlaylistControllerIntergrationTest {

    private final String URI = "/playlists";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jsonifier;
    @Autowired
    private ModelMapper mapper;

    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.mapper.map(playlist, PlaylistDTO.class);
    }

//    void createPanda() throws Exception {
//        PlaylistDTO testDTO = mapToDTO(new Playlist("Panda","Crayons","artwork"));
//        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//
//        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//
//        ResultMatcher checkStatus = status().isCreated();
//
//        this.mvc.perform(request).andExpect(checkStatus);
//    }
//
//    @Test
//    void createTest() throws Exception {
//        PlaylistDTO testDTO = mapToDTO(new Playlist("Panda","Crayons","artwork"));
//        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//
//        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//
//        ResultMatcher checkStatus = status().isCreated();
//
//        PlaylistDTO testSavedDTO = mapToDTO(new Playlist(6L,"Panda","Crayons","artwork"));
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//    }
//
//    @Test
//    void readOneTest() throws Exception {
//
//        createPanda();
//
//        RequestBuilder request = get(URI + "/read/6");
//        ResultMatcher checkStatus = status().isOk();
//
//        PlaylistDTO testSavedDTO = mapToDTO(new Playlist(6L,"Panda","Crayons","artwork"));
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//    }
//
//    @Test
//    void updateTest() throws Exception {
//
//        createPanda();
//
//        PlaylistDTO testDTO = mapToDTO(new Playlist("Dog","Bones","artwork2"));
//        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//
//        RequestBuilder request = put(URI + "/update/6").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//
//        ResultMatcher checkStatus = status().isAccepted();
//
//        PlaylistDTO testSavedDTO = mapToDTO(new Playlist(6L,"Dog","Bones","artwork2"));
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//    }

    @Test
    void deleteTest() throws Exception {

        RequestBuilder request = delete(URI + "/delete/1");
        ResultMatcher checkStatus = status().isNoContent();

        this.mvc.perform(request).andExpect(checkStatus);

    }

}
