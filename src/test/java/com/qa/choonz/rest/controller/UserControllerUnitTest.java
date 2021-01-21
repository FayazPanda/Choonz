package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
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
public class UserControllerUnitTest {

    private final User TUSER1 = new User("test", "test");
    private final User TUSER2 = new User("alice", "E");
    private final User TUSER3 = new User("dog", "cat");
    private final List<User> USERS = List.of(TUSER1, TUSER2, TUSER3);
    @Autowired
    private UserController controller;
    @MockBean
    private UserService service;
    @Autowired
    private ModelMapper mapper;

    private UserDTO maptoDto(User tasks) {
        return this.mapper.map(tasks, UserDTO.class);
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(TUSER1)).thenReturn(this.maptoDto(TUSER1));
        assertThat(new ResponseEntity<UserDTO>(this.maptoDto(TUSER1), HttpStatus.CREATED))
                .isEqualTo(this.controller.create(TUSER1));
        verify(this.service, atLeastOnce()).create(TUSER1);

    }

    @Test
    void readOneTest() throws Exception {
        when(this.service.read(TUSER1.getId())).thenReturn(this.maptoDto(TUSER1));
        assertThat(new ResponseEntity<UserDTO>(this.maptoDto(TUSER1), HttpStatus.OK))
                .isEqualTo(this.controller.read(TUSER1.getId()));
        verify(this.service, atLeastOnce()).read(TUSER1.getId());
    }

    @Test
    void readAllTest() throws Exception {
        List<UserDTO> dtos = USERS.stream().map(this::maptoDto).collect(Collectors.toList());
        when(this.service.read()).thenReturn(dtos);
        assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
        verify(this.service, atLeastOnce()).read();

    }

    @Test
    void updateTest() throws Exception {
        when(this.service.update(TUSER1, TUSER1.getId())).thenReturn(this.maptoDto(TUSER1));
        assertThat(new ResponseEntity<UserDTO>(this.maptoDto(TUSER1), HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(TUSER1,TUSER1.getId()));
        verify(this.service, atLeastOnce()).update(TUSER1, TUSER1.getId());
    }

    @Test
    void deleteTest() throws Exception {
        when(this.service.delete(TUSER1.getId())).thenReturn(false);
        assertThat(this.controller.delete(TUSER1.getId()))
                .isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        verify(this.service, atLeastOnce()).delete(TUSER1.getId());

    }

    @Test
    void deleteTest2() throws Exception {
        when(this.service.delete(TUSER1.getId())).thenReturn(true);
        assertThat(this.controller.delete(TUSER1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        verify(this.service, atLeastOnce()).delete(TUSER1.getId());
    }

}
