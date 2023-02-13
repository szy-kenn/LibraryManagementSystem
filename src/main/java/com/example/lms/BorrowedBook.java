package com.example.lms;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowedBook extends Book implements Serializable {

    LocalDate dateBorrowed;
    LocalDate dateToReturn;
    LocalDate dateReturned;
    boolean isReturned;

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturned() {
        if (!isReturned()) {
            return "-";
        }
        dateReturned = LocalDate.now();
        return dateReturned.toString();
    }

    public LocalDate getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(LocalDate dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public BorrowedBook(String bookTitle, String bookAuthor, String genre,
                        LocalDate dateBorrowed, LocalDate dateToReturn, boolean isReturned) {
        super(bookTitle, bookAuthor, genre);
        this.dateBorrowed = dateBorrowed;
        this.dateToReturn = dateToReturn;
        this.isReturned = isReturned;


    }
}
