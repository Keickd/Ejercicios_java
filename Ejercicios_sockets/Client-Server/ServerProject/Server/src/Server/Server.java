package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import Model.Book;

public class Server {
    private static List<Book> books = new ArrayList<>();
    
    Server(){
    	 initializeBooks();
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server listening in port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                Thread clientThread = new Thread(new ClientHandler(clientSocket, books));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeBooks() {
        books.add(new Book("978-123", "Harry Potter y la piedra filosofal", "J.K. Rowling", 15.99));
        books.add(new Book("978-456", "Harry Potter y la cámara secreta", "J.K. Rowling", 16.99));
        books.add(new Book("978-789", "Juego de Tronos", "George R.R. Martin", 22.00));
        books.add(new Book("978-321", "El nombre del viento", "Patrick Rothfuss", 20.00));
        books.add(new Book("978-654", "El código Da Vinci", "Dan Brown", 18.00));
    }

}
