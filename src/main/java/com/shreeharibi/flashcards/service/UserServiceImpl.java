package com.shreeharibi.flashcards.service;

import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.User;
import com.shreeharibi.flashcards.repository.RoleRepository;
import com.shreeharibi.flashcards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User service implementation class
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("Adding role {} to {}", rolename, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all user information");
        return userRepo.findAll();
    }
}
