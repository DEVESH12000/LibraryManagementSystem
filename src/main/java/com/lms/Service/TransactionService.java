package com.lms.Service;

import com.lms.Dto.TransactionDTO;
import com.lms.Entity.*;
import com.lms.Repository.BookRepository;
import com.lms.Repository.CardRepository;
import com.lms.Repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${books.max_allowed}")
    private int maxAllowedBooks;

    @Value("${books.max_allowed_days}")
    private int maxDaysAllowed;

    @Value("${books.fine.per_day}")
    private int finePerDay;

    public TransactionDTO issueBooks(long cardId, long bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book is either unavailable or not present!!"));

        if (!book.isAvailable()) {
            throw new Exception("Book is either unavailable or not present!!");
        }

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new Exception("Card is invalid!!"));

        if (card.getCardStatus() == CardStatus.DEACTIVATED) {
            throw new Exception("Card is invalid!!");
        }

        if (card.getBooks().size() >= maxAllowedBooks) {
            throw new Exception("Book limit reached for this card!!");
        }

        book.setAvailable(false);
        book.setCard(card);

        List<Book> books = card.getBooks();
        books.add(book);
        card.setBooks(books);

        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(transaction);

        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public TransactionDTO returnBooks(long cardId, long bookId) throws Exception {
        List<Transaction> transactions = transactionRepository.findByCard_Book(cardId, bookId, TransactionStatus.SUCCESSFUL, true);
        Transaction lastIssueTransaction = transactions.get(transactions.size() - 1);

        Date issueDate = lastIssueTransaction.getTransactionDate();
        long issueTime = Math.abs(issueDate.getTime() - System.currentTimeMillis());
        long numberOfDaysPassed = TimeUnit.DAYS.convert(issueTime, TimeUnit.MILLISECONDS);
        int fine = 0;

        if (numberOfDaysPassed > maxDaysAllowed) {
            fine = (int) Math.abs(numberOfDaysPassed - maxDaysAllowed) * finePerDay;
        }

        Card card = lastIssueTransaction.getCard();
        Book book = lastIssueTransaction.getBook();

        book.setCard(null);
        book.setAvailable(true);

        bookRepository.save(book);

        Transaction newTransaction = new Transaction();
        newTransaction.setBook(book);
        newTransaction.setCard(card);
        newTransaction.setFineAmount(fine);
        newTransaction.setIssueOperation(false);
        newTransaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionRepository.save(newTransaction);

        return modelMapper.map(newTransaction, TransactionDTO.class);
    }

}
