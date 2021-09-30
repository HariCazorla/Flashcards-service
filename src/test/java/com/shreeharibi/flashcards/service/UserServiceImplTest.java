package com.shreeharibi.flashcards.service;

import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.User;
import com.shreeharibi.flashcards.repository.RoleRepository;
import com.shreeharibi.flashcards.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private RoleRepository roleRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void saveUser() {
        //arrange
        User user = new User("test user", "tester", "pwd", null, List.of(new Role("ROLE_USER", null)));
        when(userRepo.save(user)).thenReturn(user);
        //act
        User result = service.saveUser(user);
        //assert
        assertThat(result)
                .isNotNull()
                .isEqualTo(user);
    }

    @Test
    void saveRole() {
        //arrange
        Role role = new Role("ROLE_ADMIN", null);
        when(roleRepo.save(role)).thenReturn(role);
        //act
        Role result = service.saveRole(role);
        //assert
        assertThat(result)
                .isNotNull()
                .isEqualTo(role);
    }

    @Test
    void addRoleToUser() {
        //arrange
        User user = new User("test user", "tester", "pwd", null, new ArrayList<Role>());
        Role role = new Role("ROLE_USER", null);
        when(userRepo.findByUsername("tester")).thenReturn(user);
        when(roleRepo.findByName("ROLE_USER")).thenReturn(role);
        //act
        service.addRoleToUser("tester", "ROLE_USER");
        //assert
        verify(userRepo, times(1)).findByUsername("tester");
        verify(roleRepo, times(1)).findByName("ROLE_USER");
    }

    @Test
    void getUser() {
        //arrange
        when(userRepo.findByUsername(any(String.class))).thenReturn(any(User.class));
        //act
        service.getUser("tester");
        //assert
        verify(userRepo, times(1)).findByUsername("tester");
    }

    @Test
    void getUsers() {
        //arrange
        when(userRepo.findAll()).thenReturn(null);
        //act
        service.getUsers();
        //assert
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void loadUserByUsername() {
        //arrange
        Role role = new Role("ROLE_USER", null);
        User user = new User("test user", "tester", "pwd", null, List.of(role));
        UserDetails userEx = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(
                    new SimpleGrantedAuthority(role.getName())
            )
        );

        when(userRepo.findByUsername(anyString())).thenReturn(user);
        //act
        UserDetails userResult = service.loadUserByUsername("tester");
        //assert
        assertThat(userResult)
                .isNotNull()
                .isEqualTo(userEx);
    }
}