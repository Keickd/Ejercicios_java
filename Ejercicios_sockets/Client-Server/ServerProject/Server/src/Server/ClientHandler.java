package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Model.Book;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Book> books;
    private static final Lock lock = new ReentrantLock();

    public ClientHandler(Socket clientSocket, List<Book> books) {
        this.clientSocket = clientSocket;
        this.books = books;
    }

    @Override
    public void run() {
        try  {
        	receiveAndSendResponse();
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
    
    private void receiveAndSendResponse() throws IOException {
	   String request;
	   boolean exitCode = false;
	   BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

       while ((request = input.readLine()) != null && !(exitCode = request.startsWith("EXIT"))) {
           if (request.startsWith("CHECK_ISBN:")) {
              findBookByISBN(request, output);
           } else if (request.startsWith("CHECK_TITLE:")) {
        	   findBookByTitle(request, output);
           } else if (request.startsWith("CHECK_BOOKS_BY_AUTHOR:")) {
               findBooksByAuthor(request, output);
           } else if (request.startsWith("TRY_ADD_BOOK:")) {
        	  addBook(request, output, input);
           } else {
               output.println("Invalid request.");  
           }
           output.println("END_RESPONSE");
       }
       if (exitCode) {
    	   System.out.println("Exiting");
       }
    }

    private void findBookByISBN(String request, PrintWriter output) {
    	 Book chosenBook = null;
    	 String isbn = request.substring("CHECK_ISBN:".length());
         
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                chosenBook = book;
                break;
            }
        }
        
        output.println(chosenBook != null ? chosenBook.toString() : "Book not found.");
    }

    private void findBookByTitle(String request, PrintWriter output) {
        Book chosenBook = null;
        String title = request.substring("CHECK_TITLE:".length());
    	
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                chosenBook = book;
                break;
            }
        }
        
        output.println(chosenBook != null ? chosenBook.toString() : "Book not found.");
    }
    
    private void findBooksByAuthor(String request, PrintWriter output) {
    	String author = request.substring("CHECK_BOOKS_BY_AUTHOR:".length()).trim();
        List<Book> booksByAuthor = books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
        
        if (booksByAuthor.isEmpty()) {
            output.println("No books found by " + author);
        } else {
            for (Book book : booksByAuthor) {
                output.println(book.toString());
            }
        } 
    }
    
    private void addBook(String request, PrintWriter output, BufferedReader input) {
    	 boolean locked = lock.tryLock();
         
         if (!locked) {
             output.println("Server busy adding a book by another client. Please wait and try again");
             return;
         }
         
         output.println("Ready to add book");
         output.println("END_RESPONSE");
         String bookRequest = null;
		try {
			bookRequest = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			output.println("Error receiving data");
			return;
		}
         
         if (bookRequest != null && bookRequest.startsWith("ADD_BOOK_REQUEST:")) {
             try {
                 String[] bookData = bookRequest.substring("ADD_BOOK_REQUEST:".length()).split(",");
                 if (bookData.length == 4) {
                     String isbn = bookData[0];
                     String title = bookData[1];
                     String author = bookData[2];
                     double price = Double.parseDouble(bookData[3]);

                     books.add(new Book(isbn, title, author, price));
                     output.println("Book added successfully");
                 } else {
                     output.println("Invalid book data");
                 }
             } finally {
                 lock.unlock();
             }
         }
    }
}
