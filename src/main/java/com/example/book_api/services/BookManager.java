package com.example.book_api.services;

import com.example.book_api.exceptions.BookAlreadyExistException;
import com.example.book_api.repositories.BookRepository;
import com.example.book_api.repositories.entities.Book;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookManager {
    private final BookRepository repo;
    public BookManager(BookRepository repo) { this.repo = repo; }

    public List<? extends Book> findAll() { return repo.findAll(); }
    public Optional<Book> findByIsbn(String isbn) { return repo.findByIsbn(isbn); }
    public boolean existsByIsbn(String isbn) { return repo.existsByIsbn(isbn); }

    @Transactional
    public Book save(Book book) {
        try { return repo.save(book); }
        catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistException("Book with the same ISBN already exists", e);
        }
    }

    @Transactional
    public boolean deleteByIsbn(String isbn) { return repo.deleteByIsbn(isbn) > 0; }

    @Transactional
    public void delete(Book book) { repo.delete(book); }
}

