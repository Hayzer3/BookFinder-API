package com.fiap.bookfinder.repository;

import com.fiap.bookfinder.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl";
    private final String user = "rm566503";
    private final String password = "201004";

    public void save(Book book) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            createTableIfNotExists(conn);
            String sql = "INSERT INTO books (title, authors, published_date) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthors());
            ps.setString(3, book.getPublishedDate());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao salvar: " + e.getMessage());
        }
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, title, authors, published_date FROM books");
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("authors"),
                        rs.getString("published_date")
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("erro ao buscar: " + e.getMessage());
        }
        return books;
    }

    private void createTableIfNotExists(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE books (" +
                    "id NUMBER GENERATED AS IDENTITY, " +
                    "title VARCHAR2(255), " +
                    "authors VARCHAR2(255), " +
                    "published_date VARCHAR2(50))");
        } catch (SQLException e) {
            // tabela já existe
        }
    }

    public boolean deleteById(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar livro: " + e.getMessage());
            return false;
        }
    }
}
