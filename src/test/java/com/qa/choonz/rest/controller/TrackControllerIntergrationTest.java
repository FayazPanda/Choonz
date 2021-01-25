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

    @Test
    void createTest() throws Exception {
        TrackDTO testDTO = mapToDTO(new Track("Panda",456,"The life of a panda is a simple one"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isCreated();

        TrackDTO testSavedDTO = mapToDTO(new Track(22L,"Panda",456,"The life of a panda is a simple one"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readOneTest() throws Exception {

        RequestBuilder request = get(URI + "/read/1");
        ResultMatcher checkStatus = status().isOk();

        TrackDTO testSavedDTO = mapToDTO(new Track(1L, "Angsty Teen",3363,"Lorem Ipsum is simply dummy text of the printing and"));
        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    void readAllTest() throws Exception {

        RequestBuilder request = get(URI + "/read");
        ResultMatcher checkStatus = status().isOk();

        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(mapToDTO(new Track(1L,"Angsty Teen",3363,"Lorem Ipsum is simply dummy text of the printing and")));
        tracks.add(mapToDTO(new Track(2L,"Benji the bulldog",985," it look like readable English. Many desktop")));
        tracks.add(mapToDTO(new Track(3L,"Can you feel it?",4517,"volved over the years, som','Can you feel it?")));
        tracks.add(mapToDTO(new Track(4L,"Did you know?",400,"have suffered alteration in some f','Did you know?")));
        tracks.add(mapToDTO(new Track(5L,"everyone ate the cheese",7781,"dle of text. All the Lorem Ipsum gener")));
        tracks.add(mapToDTO(new Track(6L,"Fnks fr mmrs",9303,"e, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem")));
        tracks.add(mapToDTO(new Track(7L,"get over it",2100,"d humour, or randomised words which dont look even slightly believable. If you are going to u")));
        tracks.add(mapToDTO(new Track(8L,"how long have you been reading this?",4126,"f over 200 Latin words, combined','how long have you been reading this?")));
        tracks.add(mapToDTO(new Track(9L,"im loosing my sanity",4257,"rem Ipsum passage, and going through the cites of the word in classical literature, discovered")));
        tracks.add(mapToDTO(new Track(10L,"jk i already lost it",6155,"ssical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Lat")));
        tracks.add(mapToDTO(new Track(11L,"kool kids",5538,"Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using")));
        tracks.add(mapToDTO(new Track(12L,"lmao",6023,"e going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarr")));
        tracks.add(mapToDTO(new Track(13L,"mr darkstreet",5778," the 1500s, when an unknown printer took a galley of type and scrambled it to ")));
        tracks.add(mapToDTO(new Track(14L,"northside",527,".10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil)")));
        tracks.add(mapToDTO(new Track(15L,"Oh look!, a cat",3617,"us Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, acco")));
        tracks.add(mapToDTO(new Track(16L,"purfect",5211,"cted by the readable content of a page when looking at its layout. The point of using Lore")));
        tracks.add(mapToDTO(new Track(17L,"quiditch",4516," galley of type and scrambled it to make a type specimen book. It has survived n")));
        tracks.add(mapToDTO(new Track(18L,"restlessness",4732,"om a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered t")));
        tracks.add(mapToDTO(new Track(19L,"street fite",4517,"oots in a piece of classical Latin literature from 45 BC, making it over 2000 years ol")));
        tracks.add(mapToDTO(new Track(20L,"Thanks god im done",3066,"f Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bono")));
        tracks.add(mapToDTO(new Track(21L,"u iz rite",12,"here can I get")));


        String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(tracks);

        ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

        this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);


    }

    @Test
    void updateTest() throws Exception {
        TrackDTO testDTO = mapToDTO(new Track("Panda",456,"The life of a panda is a simple one"));
        String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

        RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

        ResultMatcher checkStatus = status().isAccepted();

        TrackDTO testSavedDTO = mapToDTO(new Track(1L,"Panda",456,"The life of a panda is a simple one"));
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
