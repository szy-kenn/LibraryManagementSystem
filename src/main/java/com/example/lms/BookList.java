package com.example.lms;

import java.util.List;

public class BookList {

    List<Book> bookList;

    public BookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
