package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.service.CardsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("cards/add")
    public ResponseEntity<Object> addCard(
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String description
    ) {
        Card card = new Card();
        card.setTitle(title);
        card.setValue(description);
        return ResponseEntity.ok(cardsDaoService.addCard(card));
    }
}
