package com.shreeharibi.flashcards.controller;

import com.shreeharibi.flashcards.model.Card;
import com.shreeharibi.flashcards.service.CardsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlashcardControllerTest {

    @Mock
    private CardsService service;

    @InjectMocks
    private FlashcardController controller;

    @Test
    void status() {
        //arrange
        String expectedResult = "running...";
        //act
        String actualResult = controller.status();
        //assert
        assertThat(actualResult)
                .isNotNull()
                .isInstanceOf(String.class)
                .isEqualTo(expectedResult);
    }

    @Test
    void getCards() {
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
        when(service.fetchAllCards()).thenReturn(expectedCards);

        //act
        List<Card> actualCards = controller.getCards().getBody();

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
        when(service.addCard(card)).thenReturn(card);
        //act
        Object result = controller.addCard(card).getBody();
        //assert
        assertThat(result)
                .isNotNull()
                .isEqualTo(card);
    }

    @Test
    void deleteCard() {
        //arrange
        String titleOfCard = "wissen";
        doNothing().when(service).deleteCard(titleOfCard);
        //act
        controller.deleteCard(titleOfCard);
        //assert
        verify(service, times(1)).deleteCard(titleOfCard);
    }
}