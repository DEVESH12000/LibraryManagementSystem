package com.lms.Service;

import com.lms.Dto.BookDTO;

import java.util.List;

public interface BookServiceImpl {
    public BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> getBooks(String genre, boolean isAvailable, String author);

}
