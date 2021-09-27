package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.RoleToUserForm;
import com.shreeharibi.flashcards.model.User;
import com.shreeharibi.flashcards.service.UserService;
import com.shreeharibi.flashcards.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @GetMapping("/users/all")
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User>saveUser(
            @RequestBody User user
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(service.saveUser(user));
    }

    @PostMapping("/roles/save")
    public ResponseEntity<Role>saveRole(
            @RequestBody Role role
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
        return ResponseEntity.created(uri).body(service.saveRole(role));
    }

    @PostMapping("/roles/addtouser")
    public ResponseEntity addRoleToUser(
            @RequestBody RoleToUserForm form
    ) {
        service.addRoleToUser(
                form.getUsername(),
                form.getRolename()
        );
        return ResponseEntity.ok().build();
    }
}