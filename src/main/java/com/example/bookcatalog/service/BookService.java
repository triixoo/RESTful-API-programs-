package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long currentId = 1L;

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public Book addBook(Book book) {
        book.setId(currentId++);
        books.add(book);
        return book;
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAuthor(updatedBook.getAuthor());
            book.setTitle(updatedBook.getTitle());
            book.setDescription(updatedBook.getDescription());
            book.setPrice(updatedBook.getPrice());
            return book;
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}
