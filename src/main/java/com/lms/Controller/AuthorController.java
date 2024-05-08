package com.lms.Controller;

import com.lms.Dto.AuthorDTO;
import com.lms.Entity.Author;
import com.lms.Service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/createAuthor")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.createAuthor(authorDTO) ,HttpStatus.CREATED);
    }

    @PutMapping("/updateAuthor")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO,  @PathVariable(name = "id") long id) {
        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(authorDTO,id);
        return new ResponseEntity<>(updatedAuthorDTO, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deleteAuthor")
    public ResponseEntity deleteAuthor(@RequestParam("id") long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity("Author deleted!!",HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable(name = "id") Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author successfully deleted!", HttpStatus.OK);
    }
}