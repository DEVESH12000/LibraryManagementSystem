package com.lms.Controller;

import com.lms.Dto.TransactionDTO;
import com.lms.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/issueBook")
    public ResponseEntity<TransactionDTO> issueBook(@RequestBody TransactionDTO request) {
        try {
            TransactionDTO transactionDTO = transactionService.issueBooks(request.getCardId(), request.getBookId());
            return ResponseEntity.ok(transactionDTO);
        } catch (Exception e) {
            TransactionDTO errorDTO = new TransactionDTO();
            errorDTO.setErrorMessage("Failed to issue the book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<TransactionDTO> returnBook(@RequestBody TransactionDTO request) {
        try {
            TransactionDTO transactionDTO = transactionService.returnBooks(request.getCardId(), request.getBookId());
            return ResponseEntity.ok(transactionDTO);
        } catch (Exception e) {
            TransactionDTO errorDTO = new TransactionDTO();
            errorDTO.setErrorMessage("Failed to return the book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
        }
    }
}