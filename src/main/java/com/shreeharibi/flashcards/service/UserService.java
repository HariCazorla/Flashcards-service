package com.shreeharibi.flashcards.service;

import com.shreeharibi.flashcards.model.Role;
import com.shreeharibi.flashcards.model.User;

import java.util.List;

/**
 * User service contract
 */
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    List<User> getUsers();
}
