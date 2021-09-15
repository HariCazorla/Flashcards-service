package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.service.CardsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class with all REST end points
 */
@RestController
@RequestMapping("api/v1/flashcards")
public class FlashcardController {

    private final CardsDaoService cardsDaoService;
    @Autowired
    public FlashcardController(CardsDaoService cardsDaoService) {
        this.cardsDaoService = cardsDaoService;
    }

    @GetMapping("status")
    public String status() {
        return "running...";
    }

    @GetMapping("cards")
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(cardsDaoService.fetchAllCards());
    }
}
