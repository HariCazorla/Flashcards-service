package com.shreeharibi.flashcards.service;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.repository.CardsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CardsServiceTest {

    @Mock
    private CardsRepository repository;

    @InjectMocks
    private CardsService service;

    @Test
    void fetchAllCards() {
        //arrange
        List<Card> expectedCards = List.of(
                new Card(
                        "Guten Morgen",
                        "Good Morning",
                        1L
                ),

                new Card(
                        "Guten Abend",
                        "Good Evening",
                        2L
                )
        );
        when(repository.findAll()).thenReturn(expectedCards);

        //act
        List<Card> actualCards = service.fetchAllCards();

        //assert
        assertThat(actualCards)
                .isNotNull()
                .isEqualTo(expectedCards);
    }

    @Test
    void addCard() {
        //arrange
        Card card = new Card();
        card.setTitle("wissen");
        card.setValue("to know");
        when(repository.save(card)).thenReturn(card);
        //act
        Object result = service.addCard(card);
        //assert
        assertThat(result)
                .isNotNull()
                .isEqualTo(card);
    }

    @Test
    void deleteCard() {
        //arrange
        Card card = new Card();
        card.setTitle("wissen");
        card.setValue("to know");
        when(repository.findCardByName(card.getTitle())).thenReturn(java.util.Optional.of(card));
        doNothing().when(repository).delete(card);
        //act
        service.deleteCard(card.getTitle());
        //assert
        verify(repository, times(1)).delete(card);
    }
}