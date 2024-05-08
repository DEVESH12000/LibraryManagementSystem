package com.lms.Service;

import com.lms.Dto.AuthorDTO;
import com.lms.Entity.Author;
import com.lms.Exception.ResourceNotFoundException;
import com.lms.Repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements  AuthorServiceImpl{
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ModelMapper modelMapper;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }




    public AuthorDTO updateAuthor(AuthorDTO authorDTO, long id) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setEmail(authorDTO.getEmail());
        existingAuthor.setAge(authorDTO.getAge());
        existingAuthor.setCountry(authorDTO.getCountry());

        Author updatedAuthor = authorRepository.save(existingAuthor);


        ModelMapper modelMapper = new ModelMapper();


        AuthorDTO updatedAuthorDTO = modelMapper.map(updatedAuthor, AuthorDTO.class);

        return updatedAuthorDTO;
    }


    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
