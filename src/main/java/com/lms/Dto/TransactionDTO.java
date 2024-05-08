package com.lms.Dto;

import com.lms.Entity.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
public class TransactionDTO {
    private long id;
    private String transactionId;
    private int cardId;
    private int fineAmount;
    private int bookId;
    private Boolean isIssueOperation;
    private TransactionStatus transactionStatus;
    private Date transactionDate;
    private String errorMessage; // New field for error messages

    // Constructor with eight arguments
    public TransactionDTO(String transactionId, int cardId, int fineAmount, int bookId, Boolean isIssueOperation, TransactionStatus transactionStatus, Date transactionDate, String errorMessage) {
        this.transactionId = transactionId;
        this.cardId = cardId;
        this.fineAmount = fineAmount;
        this.bookId = bookId;
        this.isIssueOperation = isIssueOperation;
        this.transactionStatus = transactionStatus;
        this.transactionDate = transactionDate;
        this.errorMessage = errorMessage;
    }

    // No-argument constructor
    public TransactionDTO() {
    }




    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Boolean getIssueOperation() {
        return isIssueOperation;
    }

    public void setIssueOperation(Boolean issueOperation) {
        isIssueOperation = issueOperation;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

