package com.fiap.bookfinder.model;

public class Book {
    private String title;
    private String authors;
    private String publishedDate;

    public Book(String title, String authors, String publishedDate) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

    public Book() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }

    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String publishedDate) { this.publishedDate = publishedDate; }

    @Override
    public String toString() {
        return "Livro: " + title + " | Autor: " + authors + " | Data: " + publishedDate;
    }
}