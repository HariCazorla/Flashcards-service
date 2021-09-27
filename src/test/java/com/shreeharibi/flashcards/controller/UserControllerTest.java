package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.RoleToUserForm;
import com.shreeharibi.flashcards.model.User;
import com.shreeharibi.flashcards.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private MockHttpServletRequest mockRequest;

    /**
     * Add a MockHttpServletRequest in order to provide the needed RequestContextHolder and context path.
     */
    @BeforeEach
    public void setup() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.setContextPath("/api");
        ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attrs);
    }

    @Test
    void getUsers() {
        //arrange
        List<User> expectedUserList = List.of(
                new User(
                        "Shree Hari", "shreehari", "shreehari", null,
                        List.of(new Role("ROLE_ADMIN", null))
                )
        );
        when(service.getUsers()).thenReturn(expectedUserList);
        //act
        ResponseEntity<List<User>> actualUserList = controller.getUsers();
        //assert
        assertThat(actualUserList.getBody())
                .isNotNull()
                .isEqualTo(expectedUserList);

        assertThat(actualUserList.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    void saveRole() {
        //arrange
        Role expectedRole = new Role("ROLE_USER", null);
        when(service.saveRole(expectedRole)).thenReturn(expectedRole);
        //act
        ResponseEntity<Role> actualRole = controller.saveRole(expectedRole);
        //assert
        assertThat(actualRole.getBody())
                .isNotNull()
                .isEqualTo(expectedRole);

        assertThat(actualRole.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @Ignore
    void testSaveUser() {
        //arrange
        User expectedUser = new User("Shree Hari", "shreehari", "shreehari", null,
                List.of(new Role("ROLE_ADMIN", null)));
        when(service.saveUser(expectedUser)).thenReturn(expectedUser);
        //act
        ResponseEntity<User> actualUser = controller.saveUser(expectedUser);
        //assert
        assertThat(actualUser.getBody())
                .isNotNull()
                .isEqualTo(expectedUser);

        assertThat(actualUser.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void addRoleToUser() {
        //arrange
        doNothing().when(service).addRoleToUser("ROLE_USER", "shreehari");
        //act
        controller.addRoleToUser(new RoleToUserForm("ROLE_USER", "shreehari"));
        //assert
        verify(service, times(1)).addRoleToUser("ROLE_USER", "shreehari");
    }
}