package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Client(String host, int port) {
        while (true) {
            try {
                socket = new Socket(host, port);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Connected to server at " + host + ":" + port);
                break;
            } catch (IOException e) {
                System.out.println("Error connecting to server: " + e.getMessage());
                System.out.println("Retrying in 5 seconds...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted while waiting to retry.");
                }
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            System.out.println("\n1. Get a boook by ISBN");
            System.out.println("2. Get a book by title");
            System.out.println("3. Get books by author");
            System.out.println("4. Add a book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    getBookByISBN(scanner);
                    break;
                case "2":
                    getBookByTitle(scanner);
                    break;
                case "3":
                	getBooksByAuthor(scanner);
                	break;
                case "4":
                    addBook(scanner);
                    break;
                case "5":
                    System.out.println("Exiting");
                    exit();
                    break;
                default:
                    System.out.println("No valid option, Try again.");
            }
        } while (!option.equals("5"));

        closeConnection();
        scanner.close();
    }

    private void getBookByISBN(Scanner scanner) {
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        sendMessage("CHECK_ISBN:" + isbn);
        getResponse();
    }

    private void getBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        sendMessage("CHECK_TITLE:" + title);
        getResponse();
    }
    
    private void getBooksByAuthor(Scanner scanner) {
        System.out.print("Enter the author: ");
        String author = scanner.nextLine();
        sendMessage("CHECK_BOOKS_BY_AUTHOR:" + author);
        getResponse();
    }
    
    private void addBook(Scanner scanner) {
        sendMessage("TRY_ADD_BOOK:");
        if (!getResponse()) { 
            return;
        }

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        String price = scanner.nextLine();

        sendMessage("ADD_BOOK_REQUEST:" + isbn + "," + title + "," + author + "," + price);
        getResponse();
    }
    
    private void sendMessage(String message) {
        output.println(message);
    }

    private boolean getResponse() {
        String line;
        boolean success = false;

        try {
            while ((line = input.readLine()) != null) {
                if (line.equals("END_RESPONSE")) {
                    break;
                }
                System.out.println("Server response: " + line);
                
                if (line.equals("Ready to add book")) {
                    success = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error receiving response: " + e.getMessage());
        }
        return success; 
    }


    private void closeConnection() {
        try {
            input.close();
            output.close();
            socket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    
    private void exit(){
    	sendMessage("EXIT");
    }

}
