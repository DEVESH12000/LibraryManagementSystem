package com.lms.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Change data type to int

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    private Date createdOn;

    private Date updatedOn;

    @Enumerated(value=EnumType.STRING)
    private CardStatus cardStatus;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> books;

    public Card(){
        // No automatic setting of cardStatus
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getId() {  // Change return type to int
        return id;
    }

    public void setId(int id) {  // Change parameter type to int
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<Transaction> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<>(); // Initialize the list if it's null
        }
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
