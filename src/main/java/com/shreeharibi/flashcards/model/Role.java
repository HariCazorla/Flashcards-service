package com.shreeharibi.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
}
