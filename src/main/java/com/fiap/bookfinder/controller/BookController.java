package com.fiap.bookfinder.controller;

import com.fiap.bookfinder.service.BookService;
import com.fiap.bookfinder.repository.BookRepository;
import com.fiap.bookfinder.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Scanner;

public class BookController {
    private final BookService service = new BookService();
    private final BookRepository repository = new BookRepository();
    private final Scanner sc = new Scanner(System.in);
    private final ObjectMapper mapper = new ObjectMapper();

    public void search(String query) {
        List<Book> books = service.searchBooks(query);
    }

    // para buscar os livros da openlibrary
    public String searchBooksApi(String query) {
        try {
            List<Book> books = service.searchBooks(query);
            return mapper.writeValueAsString(books);
        } catch (Exception e) {
            return "{\"error\": \"Erro na busca: " + e.getMessage() + "\"}";
        }
    }

    // Para API REST - Buscar livros salvos
    public String getSavedBooksApi() {
        try {
            List<Book> savedBooks = repository.findAll();
            if (savedBooks.isEmpty()) {
                return "{\"message\": \"Nenhum livro salvo encontrado\", \"books\": []}";
            }
            return mapper.writeValueAsString(savedBooks);
        } catch (Exception e) {
            return "{\"error\": \"Erro ao buscar livros salvos: " + e.getMessage() + "\"}";
        }
    }

    // para salvar livro
    public String saveBookApi(String bookJson) {
        try {
            Book book = mapper.readValue(bookJson, Book.class);
            repository.save(book);
            return "{\"message\": \"Livro salvo com sucesso!\", \"book\": " + bookJson + "}";
        } catch (Exception e) {
            return "{\"error\": \"Erro ao salvar livro: " + e.getMessage() + "\"}";
        }
    }

    public String deleteBookApi(String id) {
        try {
            int bookId = Integer.parseInt(id);
            boolean deleted = repository.deleteById(bookId);

            if (deleted) {
                return "{\"message\": \" Livro deletado com sucesso!\", \"id\": " + bookId + "}";
            } else {
                return "{\"error\": \" Livro não encontrado\", \"id\": " + bookId + "}";
            }
        } catch (NumberFormatException e) {
            return "{\"error\": \" ID inválido\"}";
        } catch (Exception e) {
            return "{\"error\": \" Erro ao deletar livro: " + e.getMessage() + "\"}";
        }
    }
}