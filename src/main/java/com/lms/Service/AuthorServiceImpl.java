package com.lms.Service;

import com.lms.Dto.AuthorDTO;

public interface AuthorServiceImpl {
        public AuthorDTO createAuthor(AuthorDTO authorDTO);
        public AuthorDTO updateAuthor(AuthorDTO authorDTO,long id);
        void deleteAuthor(long id);
    }


