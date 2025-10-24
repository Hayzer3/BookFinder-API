package com.fiap.bookfinder.service;

import com.fiap.bookfinder.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        try {
            String url = "https://openlibrary.org/search.json?q=" + query.replace(" ", "+") + "&limit=10";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = mapper.readTree(response.body());
            JsonNode docs = root.path("docs");

            for (JsonNode doc : docs) {
                String title = doc.path("title").asText("sem título");
                String author = doc.path("author_name").isArray() ? doc.path("author_name").get(0).asText("desconhecido") : "desconhecido";
                String year = doc.path("first_publish_year").asText("n/a");
                books.add(new Book(title, author, year));
            }
        } catch (Exception e) {
            System.out.println("erro ao buscar livros: " + e.getMessage());
        }
        return books;
    }
}
