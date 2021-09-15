package com.shreeharibi.flashcards.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class to describe card properties
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "class representing cards")
@Table(name = "cards")
public class Card {
    @ApiModelProperty(required = true, allowEmptyValue = false)
    public String title;
    @ApiModelProperty(required = true, allowEmptyValue = false)
    public String value;

    @Id
    @SequenceGenerator(
            name = "cards_sequence",
            sequenceName = "cards_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cards_sequence"
    )
    @ApiModelProperty(required = false, allowEmptyValue = false)
    private Long id;
    @Override
    public String toString() {
        return "Card{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
