package com.lms.Service;

import com.lms.Dto.BookDTO;
import com.lms.Entity.Author;
import com.lms.Entity.Book;
import com.lms.Exception.ResourceNotFoundException;
import com.lms.Repository.AuthorRepository;
import com.lms.Repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthorRepository authorRepository;

    public BookDTO createBook(BookDTO bookDTO) {

        Long authorId = bookDTO.getAuthorId();
        if (authorId == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author","id", authorId));

        Book bookEntity = modelMapper.map(bookDTO, Book.class);
        bookEntity.setAuthor(author);
        Book savedBook = bookRepository.save(bookEntity);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    //Either giving you all the available books
    //OR gicing you all the non-available books
    public List<BookDTO> getBooks(String genre, boolean isAvailable,String author){

        List<Book> books;
        if (genre != null && author != null) {
            books = bookRepository.findBooksByGenre_Author(genre, author, isAvailable);
        } else if (genre != null) {
            books = bookRepository.findBooksByGenre(genre, isAvailable);
        } else if (author != null) {
            books = bookRepository.findBooksByAuthor(author, isAvailable);
        } else {
            books = bookRepository.findBooksByAvailability(isAvailable);
        }

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

}