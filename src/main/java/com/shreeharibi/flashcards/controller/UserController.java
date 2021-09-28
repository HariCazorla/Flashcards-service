package com.shreeharibi.flashcards.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.RoleToUserForm;
import com.shreeharibi.flashcards.model.User;
import com.shreeharibi.flashcards.service.UserService;
import com.shreeharibi.flashcards.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
    public ResponseEntity<?> addRoleToUser(
            @RequestBody RoleToUserForm form
    ) {
        service.addRoleToUser(
                form.getUsername(),
                form.getRolename()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("JSADLJASLK".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();

                User user = service.getUser(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername()) // can be any string which is unique
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //10 min expiry time
                        .withIssuer(request.getRequestURL().toString()) //company name or author
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                response.setHeader("access_token", accessToken);
                response.setHeader("refresh_token", refreshToken);
            } catch (Exception e) {
                log.error("Error logging in: {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.sendError(HttpStatus.FORBIDDEN.value());
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}