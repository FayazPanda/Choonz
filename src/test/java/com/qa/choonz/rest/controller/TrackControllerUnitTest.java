package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TrackControllerUnitTest {

    private final Track TTRACK1 = new Track("Angsty Teen",3363,"Lorem Ipsum is simply dummy text of the printing and");
    private final Track TTRACK2 = new Track("Benji the bulldog",985," it look like readable English. Many desktop");
    private final Track TTRACK3 = new Track("Can you feel it?",4517,"volved over the years, som','Can you feel it?");
    private final Track TTRACK4 = new Track("Did you know?",400,"have suffered alteration in some f','Did you know?");
    private final Track TTRACK5 = new Track("everyone ate the cheese",7781,"dle of text. All the Lorem Ipsum gener");
    private final Track TTRACK6 = new Track("Fnks fr mmrs",9303,"e, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem");
    private final Track TTRACK7 = new Track("get over it",2100,"d humour, or randomised words which dont look even slightly believable. If you are going to u");
    private final Track TTRACK8 = new Track("how long have you been reading this?",4126,"f over 200 Latin words, combined','how long have you been reading this?");
    private final Track TTRACK9 = new Track("im loosing my sanity",4257,"rem Ipsum passage, and going through the cites of the word in classical literature, discovered");
    private final Track TTRACK10 = new Track("jk i already lost it",6155,"ssical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Lat");
    private final Track TTRACK11 = new Track("kool kids",5538,"Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using");
    private final Track TTRACK12 = new Track("lmao",6023,"e going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarr");
    private final Track TTRACK13 = new Track("mr darkstreet",5778," the 1500s, when an unknown printer took a galley of type and scrambled it to ");
    private final Track TTRACK14 = new Track("northside",527,".10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil)");
    private final Track TTRACK15 = new Track("Oh look!, a cat",3617,"us Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, acco");
    private final Track TTRACK16 = new Track("purfect",5211,"cted by the readable content of a page when looking at its layout. The point of using Lore");
    private final Track TTRACK17 = new Track("quiditch",4516," galley of type and scrambled it to make a type specimen book. It has survived n");
    private final Track TTRACK18 = new Track("restlessness",4732,"om a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered t");
    private final Track TTRACK19 = new Track("street fite",4517,"oots in a piece of classical Latin literature from 45 BC, making it over 2000 years ol");
    private final Track TTRACK20 = new Track("Thanks god im done",3066,"f Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bono");
    private final Track TTRACK21 = new Track("u iz rite",12,"here can I get");

    private final List<Track> TRACKS = List.of(TTRACK1,TTRACK2,TTRACK3,TTRACK4,TTRACK5,TTRACK6,TTRACK7,TTRACK8,TTRACK19,TTRACK10,TTRACK11,TTRACK12,TTRACK13,TTRACK14,TTRACK15,TTRACK16,TTRACK17,TTRACK18,TTRACK19,TTRACK20,TTRACK21);
    @Autowired
    private TrackController controller;
    @MockBean
    private TrackService service;
    @Autowired
    private ModelMapper mapper;

    private TrackDTO maptoDto(Track tasks) {
        return this.mapper.map(tasks, TrackDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TTRACK1)).thenReturn(this.maptoDto(TTRACK1));
        assertThat(new ResponseEntity<TrackDTO>(this.maptoDto(TTRACK1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TTRACK1));
        verify(this.service, atLeastOnce()).create(TTRACK1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TTRACK1.getId())).thenReturn(this.maptoDto(TTRACK1));
        assertThat(new ResponseEntity<TrackDTO>(this.maptoDto(TTRACK1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TTRACK1.getId()));
        verify(this.service, atLeastOnce()).read(TTRACK1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<TrackDTO> dtos = TRACKS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TTRACK1, TTRACK1.getId())).thenReturn(this.maptoDto(TTRACK1));
        assertThat(new ResponseEntity<TrackDTO>(this.maptoDto(TTRACK1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TTRACK1,TTRACK1.getId()));
        verify(this.service, atLeastOnce()).update(TTRACK1, TTRACK1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TTRACK1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TTRACK1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TTRACK1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TTRACK1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TTRACK1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TTRACK1.getId());
    }

}
