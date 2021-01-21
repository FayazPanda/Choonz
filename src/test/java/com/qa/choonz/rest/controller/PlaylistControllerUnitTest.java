package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;
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
public class PlaylistControllerUnitTest {

    private final Playlist TPLAYLIST1 = new Playlist("aardvarks","when you go on one of those cheese binges","https://cms-assets.tutsplus.com/uploads/users/114/posts/34296/image/Final-image.jpg");
    private final Playlist TPLAYLIST2 = new Playlist("beetle joose","just a barrel of juice","https://www.digitalartsonline.co.uk/cmsdata/slideshow/3776245/beck_-_hyperspace.jpg");
    private final Playlist TPLAYLIST3 = new Playlist("canary opener","I lost the keys to my house playlist","https://www.digitalmusicnews.com/wp-content/uploads/2020/04/DaBaby-Blame-It-On-Baby.jpg");
    private final Playlist TPLAYLIST4 = new Playlist("dairy dialect","I saw this on facebook","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmDcpTFXJ_bv1dga9Bag1ryJ7xEY3ztLwAyg&usqp=CAU");
    private final Playlist TPLAYLIST5 = new Playlist("equidistant parabolas","dont try this at homekids","https://www.sleek-mag.com/wp-content/uploads/2016/08/AlbumCovers_Blonde.jpg");
    private final List<Playlist> PLAYLISTS = List.of(TPLAYLIST1, TPLAYLIST2, TPLAYLIST3, TPLAYLIST4,TPLAYLIST5);
    @Autowired
    private PlaylistController controller;
    @MockBean
    private PlaylistService service;
    @Autowired
    private ModelMapper mapper;

    private PlaylistDTO maptoDto(Playlist tasks) {
        return this.mapper.map(tasks, PlaylistDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TPLAYLIST1)).thenReturn(this.maptoDto(TPLAYLIST1));
        assertThat(new ResponseEntity<PlaylistDTO>(this.maptoDto(TPLAYLIST1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TPLAYLIST1));
        verify(this.service, atLeastOnce()).create(TPLAYLIST1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TPLAYLIST1.getId())).thenReturn(this.maptoDto(TPLAYLIST1));
        assertThat(new ResponseEntity<PlaylistDTO>(this.maptoDto(TPLAYLIST1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TPLAYLIST1.getId()));
        verify(this.service, atLeastOnce()).read(TPLAYLIST1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<PlaylistDTO> dtos = PLAYLISTS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TPLAYLIST1, TPLAYLIST1.getId())).thenReturn(this.maptoDto(TPLAYLIST1));
        assertThat(new ResponseEntity<PlaylistDTO>(this.maptoDto(TPLAYLIST1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TPLAYLIST1,TPLAYLIST1.getId()));
        verify(this.service, atLeastOnce()).update(TPLAYLIST1, TPLAYLIST1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TPLAYLIST1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TPLAYLIST1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TPLAYLIST1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TPLAYLIST1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TPLAYLIST1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TPLAYLIST1.getId());
    }

}
