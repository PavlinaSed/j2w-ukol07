package cz.czechitas.java2webapps.ukol7.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String slug;
    private String author;
    private String title;
    private String perex;
    private String body;
    private LocalDate published;
}
