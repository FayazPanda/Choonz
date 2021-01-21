package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;
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
public class AlbumControllerUnitTest {

    private final Album TALBUM1 = new Album("Alone in the Dark","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/artistic-album-cover-design-template-d12ef0296af80b58363dc0deef077ecc_screen.jpg?ts=1561488440");
    private final Album TALBUM2 = new Album("Bright day outside","https://img.apmcdn.org/4f25ecdbbd7af5fed833153302515a94c990de11/square/7aacc5-20130508-favorite-album-covers.jpg");
    private final Album TALBUM3 = new Album("Can you hear the music","https://glide-assets-media.s3.amazonaws.com/wp-content/uploads/2013/10/21170759/Queen-The-Miracle.jpg");
    private final Album TALBUM4 = new Album("Did you know?","https://i.pinimg.com/originals/3a/f0/e5/3af0e55ea66ea69e35145fb108b4a636.jpg");
    private final Album TALBUM5 = new Album("E","https://i.kym-cdn.com/photos/images/newsfeed/001/365/818/183.jpg");
    private final List<Album> ALBUMS = List.of(TALBUM1, TALBUM2, TALBUM3, TALBUM4,TALBUM5);
    @Autowired
    private AlbumController controller;
    @MockBean
    private AlbumService service;
    @Autowired
    private ModelMapper mapper;

    private AlbumDTO maptoDto(Album tasks) {
        return this.mapper.map(tasks, AlbumDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TALBUM1)).thenReturn(this.maptoDto(TALBUM1));
        assertThat(new ResponseEntity<AlbumDTO>(this.maptoDto(TALBUM1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TALBUM1));
        verify(this.service, atLeastOnce()).create(TALBUM1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TALBUM1.getId())).thenReturn(this.maptoDto(TALBUM1));
        assertThat(new ResponseEntity<AlbumDTO>(this.maptoDto(TALBUM1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TALBUM1.getId()));
        verify(this.service, atLeastOnce()).read(TALBUM1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<AlbumDTO> dtos = ALBUMS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TALBUM1, TALBUM1.getId())).thenReturn(this.maptoDto(TALBUM1));
        assertThat(new ResponseEntity<AlbumDTO>(this.maptoDto(TALBUM1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TALBUM1, TALBUM1.getId()));
        verify(this.service, atLeastOnce()).update(TALBUM1, TALBUM1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TALBUM1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TALBUM1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TALBUM1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TALBUM1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TALBUM1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TALBUM1.getId());
    }

}
