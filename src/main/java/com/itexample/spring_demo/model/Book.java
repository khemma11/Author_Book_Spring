package com.itexample.spring_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="book")

public class Book {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;


}
