package com.lms.Controller;

import com.lms.Dto.BookDTO;
import com.lms.Entity.Book;
import com.lms.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        if (bookDTO.getAuthorId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
return  new ResponseEntity<>(bookService.createBook(bookDTO),HttpStatus.CREATED);
    }
    @GetMapping("/getBooks")
    public ResponseEntity<List<BookDTO>>getBooks(@RequestParam(value = "genre",required = false) String genre,
                                   @RequestParam(value = "available",required = false,defaultValue = "false") boolean available,
                                   @RequestParam(value = "author",required = false) String author){

        List<BookDTO> bookDtoList=bookService.getBooks(genre,available,author);
        return new ResponseEntity<>(bookDtoList,HttpStatus.OK);
    }
}