package com.fiap.bookfinder;

import com.fiap.bookfinder.config.BookFinderHttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando BookFinder...");

        BookFinderHttpServer apiServer = new BookFinderHttpServer();
        apiServer.start(8080);
    }
}