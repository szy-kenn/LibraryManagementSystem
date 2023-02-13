package com.example.lms;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Archive implements Serializable {

    List<BorrowedBook> borrowedBooks = new ArrayList<>();

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<BorrowedBook> getReturnedBooks() {

        List<BorrowedBook> returnedBooks = new ArrayList<>();

        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.isReturned()) {
                returnedBooks.add(borrowedBook);
            }
        }
        return returnedBooks;
    }

    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
        List<BorrowedBook> currentlyBorrowedBooks = new ArrayList<>();

        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (!borrowedBook.isReturned()) {
                currentlyBorrowedBooks.add(borrowedBook);
            }
        }
        return currentlyBorrowedBooks;
    }
    public void addBook(BorrowedBook book) throws IOException {
        borrowedBooks.add(book);
    }

}
