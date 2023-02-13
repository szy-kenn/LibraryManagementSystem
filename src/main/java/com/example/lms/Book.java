package com.example.lms;

import java.io.Serializable;

public class Book implements Serializable {

    String bookTitle;
    String bookAuthor;
    String genre;

    public Book(String bookTitle, String bookAuthor, String genre) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.genre = genre;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
