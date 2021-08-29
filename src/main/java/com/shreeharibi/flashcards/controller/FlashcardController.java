package com.shreeharibi.flashcards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flashcards")
public class FlashcardController {

    @GetMapping("status")
    public String status() {
        return "running...";
    }
}
