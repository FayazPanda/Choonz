//package com.qa.choonz.rest.controller;
//
//import com.example.demo.dto.PlaylistDTO;
//import com.example.demo.persistence.domain.Playlist;
//import com.example.demo.persistence.domain.Track;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.qa.choonz.persistence.domain.Playlist;
//import com.qa.choonz.persistence.domain.Track;
//import com.qa.choonz.rest.dto.PlaylistDTO;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Sql(scripts = {"classpath:todolist-schema.sql",
//        "classpath:todolist-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles(profiles = "test")
//public class PlaylistControllerIntergrationTest {
//
//    private final String URI = "/tasklist";
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private ObjectMapper jsonifier;
//    @Autowired
//    private ModelMapper mapper;
//
//    private PlaylistDTO mapToDTO(Playlist taskList) {
//        return this.mapper.map(taskList, PlaylistDTO.class);
//    }
//
//    @Test
//    void createTest() throws Exception {
//        PlaylistDTO testDTO = mapToDTO(new Playlist("Pandamonium"));
//        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//
//        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//
//        ResultMatcher checkStatus = status().isCreated();
//
//        PlaylistDTO testSavedDTO = mapToDTO(new Playlist(3L, "Pandamonium"));
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
//        RequestBuilder request = get(URI + "/read/1");
//        ResultMatcher checkStatus = status().isOk();
//
//        Playlist LTASK1 = new Playlist(1L, "Grocery");
//        List<Track> TASKS1 = List.of(new Track(1L, "Milk"), new Track(2L, "Eggs"), new Track(3L, "Flour"));
//        LTASK1.setTrack(TASKS1);
//
//        PlaylistDTO testSavedDTO = mapToDTO(LTASK1);
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//    }
//
//    @Test
//    void readAllTest() throws Exception {
//
//        RequestBuilder request = get(URI + "/read");
//        ResultMatcher checkStatus = status().isOk();
//
//        Playlist LTASK1 = new Playlist(1L, "Grocery");
//        List<Track> TASKS1 = List.of(new Track(1L, "Milk"), new Track(2L, "Eggs"), new Track(3L, "Flour"));
//        LTASK1.setTrack(TASKS1);
//        PlaylistDTO TASK1DTO = mapToDTO(LTASK1);
//        Playlist LTASK2 = new Playlist(2L, "Stationary");
//        List<Track> TASKS2 = List.of(new Track(4L, "Toast"));
//        LTASK2.setTrack(TASKS2);
//        PlaylistDTO TASK2DTO = mapToDTO(LTASK2);
//        List<PlaylistDTO> taskList = List.of(TASK1DTO, TASK2DTO);
//
//
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(taskList);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//
//    }
//
//    @Test
//    void updateTest() throws Exception {
//        PlaylistDTO testDTO = mapToDTO(new Playlist("Pandamonium"));
//        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//
//        RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//
//        ResultMatcher checkStatus = status().isAccepted();
//
//        Playlist LTASK1 = new Playlist(1L, "Pandamonium");
//        List<Track> TASKS1 = List.of(new Track(1L, "Milk"), new Track(2L, "Eggs"), new Track(3L, "Flour"));
//        LTASK1.setTrack(TASKS1);
//        PlaylistDTO TASK1DTO = mapToDTO(LTASK1);
//
//        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(TASK1DTO);
//
//        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//
//        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//
//    }
//
//}
