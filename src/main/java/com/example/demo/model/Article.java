package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String details;
    private String category;

    public Article()
    {

    }
    public Article(@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("details") String details,
                   @JsonProperty("category") String category) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getCategory() {
        return category;
    }
}
