package com.shreeharibi.flashcards.service;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class to fetch information from postgres db
 */
@Service
public class CardsDaoService {
    private final CardsRepository cardsRepository;
    @Autowired
    public CardsDaoService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * Returns a list of all available cards
     * @return
     */
    public List<Card> fetchAllCards() {
        List<Card> result =cardsRepository.findAll();
        result.stream()
                .forEach(card -> {
                    System.out.println(card);
                });
        return result;
    }
}
