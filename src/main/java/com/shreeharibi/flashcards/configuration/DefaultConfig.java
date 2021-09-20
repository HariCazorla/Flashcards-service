package com.shreeharibi.flashcards.configuration;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.repository.CardsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Class to add some default cards on startup
 */
@Configuration
public class DefaultConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CardsRepository cardsRepository
    ) {
        return args -> {
            Card card1 = new Card(
                    "Guten Morgen",
                    "Good Morning",
                    1L
            );

            Card card2 = new Card(
                    "Guten Abend",
                    "Good Evening",
                    2L
            );

            Card card3 = new Card(
                    "Junge",
                    "Boy",
                    3L
            );

            Card card4 = new Card(
                    "Maedchen",
                    "girl",
                    4L
            );

            Card card5 = new Card(
                    "Frau",
                    "Women",
                    5L
            );

            Card card6 = new Card(
                    "Kinder",
                    "kids",
                    6L
            );

            Card card7 = new Card(
                    "Hund",
                    "Dog",
                    7L
            );

            Card card8 = new Card(
                    "Katze",
                    "Cat",
                    8L
            );

            cardsRepository.saveAll(
                    List.of(
                            card1,
                            card2,
                            card3,
                            card4,
                            card4,
                            card5,
                            card6,
                            card7,
                            card8
                    )
            );
        };
    }
}
