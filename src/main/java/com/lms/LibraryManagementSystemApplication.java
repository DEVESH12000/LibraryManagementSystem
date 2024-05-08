package com.lms;

import com.lms.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lms")
public class LibraryManagementSystemApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	@Autowired
	CardRepository cardRepository;


	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

@Autowired
	TransactionRepository transactionRepository;

@Autowired
TokenRepository tokenRepository;
	public static void main(String[] args) {

		SpringApplication.run( LibraryManagementSystemApplication.class,args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args)throws Exception {

	}
}

