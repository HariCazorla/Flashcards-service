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

            cardsRepository.saveAll(
                    List.of(
                            card1,
                            card2
                    )
            );
        };
    }
}
