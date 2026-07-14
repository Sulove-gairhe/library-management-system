package com.example.library.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> update(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setCategory(bookDetails.getCategory());
            book.setQuantity(bookDetails.getQuantity());
            return bookRepository.save(book);
        });
    }

    public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }
}
