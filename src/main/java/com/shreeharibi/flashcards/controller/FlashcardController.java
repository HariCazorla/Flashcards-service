package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.service.CardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FlashcardController.class);
    private final CardsService cardsDaoService;
    @Autowired
    public FlashcardController(CardsService cardsDaoService) {
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
            @RequestBody(required = true) Card card
    ) {
        logger.info("Adding crad " + card);
        return ResponseEntity.ok(cardsDaoService.addCard(card));
    }

    @DeleteMapping("cards/delete")
    public void deleteCard(
            @RequestParam(required = true) String name
    ) {
        cardsDaoService.deleteCard(name);
    }
}
