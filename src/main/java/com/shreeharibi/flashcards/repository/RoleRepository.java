package com.shreeharibi.flashcards.repository;

import com.shreeharibi.flashcards.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findByName(String rolename);
}
