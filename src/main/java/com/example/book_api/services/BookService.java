package com.example.book_api.services;

import com.example.book_api.repositories.BookRepository;
import com.example.book_api.repositories.entities.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository repo;
    public BookService(BookRepository repo) { this.repo = repo; }

    public List<? extends Book> getAll() { return repo.findAll(); }
    public Optional<Book> getByIsbn(String isbn) { return repo.findByIsbn(isbn); }
    public boolean existByIsbn(String isbn) { return repo.existByIsbn(isbn); }

    @Transactional
    public Book save(Book book) { return repo.save(book); }
    @Transactional
    public void delete(Book book) { repo.delete(book); }
}
