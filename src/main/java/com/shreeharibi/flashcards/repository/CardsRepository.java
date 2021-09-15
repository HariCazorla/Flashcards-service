package com.shreeharibi.flashcards.repository;

import com.shreeharibi.flashcards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Cards repository class
 */
@Repository
public interface CardsRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.title = ?1")
    Optional<Card> findExpenseByName(String name);
}
