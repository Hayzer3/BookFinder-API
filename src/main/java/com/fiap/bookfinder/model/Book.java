package com.fiap.bookfinder.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private String thumbnail;

    // Construtor vazio
    public Book() {}

    // Construtor com parâmetros
    public Book(String id, String title, String author, String description, String thumbnail) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
}