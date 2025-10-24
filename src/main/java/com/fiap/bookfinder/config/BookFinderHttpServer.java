package com.fiap.bookfinder.config;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.fiap.bookfinder.controller.BookController;
import java.io.IOException;
import java.net.InetSocketAddress;

public class BookFinderHttpServer {
    private final BookController controller = new BookController();

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/books/search", this::handleSearch);
        server.createContext("/api/books/saved", this::handleGetSaved);
        server.createContext("/api/books/save", this::handleSave);
        server.createContext("/api/books/delete", this::handleDelete);
        server.start();
        System.out.println("api rodando em http://localhost:" + port);
    }

    private void handleSearch(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        String query = "";
        String q = exchange.getRequestURI().getQuery();
        if (q != null && q.startsWith("q=")) query = q.substring(2);
        String response = controller.searchBooksApi(query);
        sendJson(exchange, 200, response);
    }

    private void handleGetSaved(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        String response = controller.getSavedBooksApi();
        sendJson(exchange, 200, response);
    }

    private void handleSave(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        try {
            String body = new String(exchange.getRequestBody().readAllBytes());
            String response = controller.saveBookApi(body);
            sendJson(exchange, 201, response);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        if (!"DELETE".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            // Extrai o ID da URL (ex: /api/books/delete/123)
            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            String response = controller.deleteBookApi(id);
            sendJson(exchange, 200, response);
        } catch (Exception e) {
            sendJson(exchange, 500, "{\"error\":\"Erro ao processar delete: " + e.getMessage() + "\"}");
        }
    }

    private void sendJson(HttpExchange exchange, int status, String body) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        byte[] bytes = body.getBytes();
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
