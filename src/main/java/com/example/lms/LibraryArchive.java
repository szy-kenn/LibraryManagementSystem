package com.example.lms;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class LibraryArchive implements Serializable {

    public List<User> usersList;

    // stores the book, its available stock and the number of times it has been borrowed
    public Map<Book, List<Integer>> booksList;

    public int totalMemberCount;

    public int todayMemberCount;

    public int booksBorrowedCount;

    public List<User> getUsersList() {
        return usersList;
    }

    public int booksReturnedCount;

    public LibraryArchive () {
        usersList = new ArrayList<>();
        booksList = new HashMap<>();
        Book BOOK001 = new Book("THE FAULT IN OUR STARS", "JOHN GREEN", "ROMANCE");
        Book BOOK002 = new Book("THE SILENT PATIENT", "ALEX MICHAELIDES", "MYSTERY");
        Book BOOK003 = new Book("ALICE'S ADVENTURES IN WONDERLAND", "LEWIS CARROLL", "KIDS");
        Book BOOK004 = new Book("ANNE OF GREEN GABLES", "L.M. MONTGOMERY", "KIDS");
        Book BOOK005 = new Book("FIFTY SHADES OF GREY", "E.L. JAMES", "ROMANCE");
        Book BOOK006 = new Book("HARRY POTTER AND THE SORCERER'S STONE", "J.K. ROWLING", "FANTASY");
        Book BOOK007 = new Book("HOWL'S MOVING CASTLE", "DIANA WYNNE JONES", "FANTASY");
        Book BOOK008 = new Book("THE DA VINCI CODE", "DAN BROWN", "MYSTERY");
        Book BOOK009 = new Book("THE HOBBIT", "J.R.R. TOLKIEN", "FANTASY");
        Book BOOK010 = new Book("THE LITTLE PRINCE", "ANTOINE DE SAINT-EXUPÉRY", "KIDS");
        Book BOOK011 = new Book("TO ALL THE BOYS I'VE LOVED BEFORE", "JENNY HAN", "ROMANCE");
        Book BOOK012 = new Book("WHERE THE CRAWDADS SING", "DELIA OWENS", "MYSTERY");
        Book BOOK013 = new Book("BRIDGE TO TERABITHIA", "KATHERINE PATERSON", "KIDS");
        Book BOOK014 = new Book("CHARLIE AND THE CHOCOLATE FACTORY", "ROALD DAHL", "KIDS");
        Book BOOK015 = new Book("THE SONG OF ACHILLES", "MADELINE MILLER", "ROMANCE");

        booksList.put(BOOK001, new ArrayList<>(Arrays.asList(5, 0)));
        booksList.put(BOOK002, new ArrayList<>(Arrays.asList(4, 0)));
        booksList.put(BOOK003, new ArrayList<>(Arrays.asList(3, 0)));
        booksList.put(BOOK004, new ArrayList<>(Arrays.asList(6, 0)));
        booksList.put(BOOK005, new ArrayList<>(Arrays.asList(2, 0)));
        booksList.put(BOOK006, new ArrayList<>(Arrays.asList(10, 0)));
        booksList.put(BOOK007, new ArrayList<>(Arrays.asList(8, 0)));
        booksList.put(BOOK008, new ArrayList<>(Arrays.asList(16, 0)));
        booksList.put(BOOK009, new ArrayList<>(Arrays.asList(7, 0)));
        booksList.put(BOOK010, new ArrayList<>(Arrays.asList(2, 0)));
        booksList.put(BOOK011, new ArrayList<>(Arrays.asList(10, 0)));
        booksList.put(BOOK012, new ArrayList<>(Arrays.asList(9, 0)));
    }

    public int getTotalMemberCount() {
        totalMemberCount = usersList.size();
        return totalMemberCount;
    }

    public int getAvailableStock(Book book) {
        /* returns -1 if the book is not in the library */
        for (Book book1 : booksList.keySet()) {
            if (book1.getBookTitle().equals(book.getBookTitle())) {
                return (booksList.get(book1).get(0));
            }
        }
        return -1;
    }

    public void addToArchive(Book book, int stock) {

    }

    private void changeStock(Book book, int num) {
        int currentStock = booksList.get(book).get(0);
        booksList.get(book).set(0, currentStock+num);
    }
    private void addBorrowCount(Book book) {
        int currentBorrowCount = booksList.get(book).get(1);
        booksList.get(book).set(1, currentBorrowCount+1);
    }
    public Map<Book, List<Integer>> getBooksList() {
        return booksList;
    }

    public List<Book> getAllBooks() {
        return booksList.keySet().stream().toList();
    }

    public Map<User, List<BorrowedBook>> getAllBorrowedBooks() {
        Map<User, List<BorrowedBook>> allBorrowedBooks = new HashMap<>();
        for (User user: usersList) {
            allBorrowedBooks.put(user, user.getArchive().getBorrowedBooks());
        }
        return allBorrowedBooks;
    }

    public List<BorrowedBook> getBorrowedBookFromUser(User user) {
        return user.getArchive().getBorrowedBooks();
    }

    public int getBooksBorrowedCount() {
        booksBorrowedCount = 0;
        for (User user: usersList) {
            booksBorrowedCount+=user.getArchive().getBorrowedBooks().size();
        }
        return booksBorrowedCount;
    }

    public void addUser(User user) throws IOException {
        usersList.add(user);
        saveToFile();
    }
    public BorrowedBook returnBook(User user, BorrowedBook book) throws IOException {
        List<BorrowedBook> borrowedBooks =user.getArchive().getBorrowedBooks();
        borrowedBooks.get(borrowedBooks.indexOf(book)).setReturned(true);
        System.out.println("Book is returned");

        for (Book book1: getAllBooks()) {
            if (book1.getBookTitle().equalsIgnoreCase(book.getBookTitle())) {
                changeStock(book1, 1);
//                user.saveToFile();
                saveToFile();
                return book;
            }
        }
        return null;
    }

    public void borrowBook(User user, BorrowedBook book) throws IOException {
        for (Book book1 : getAllBooks()){
            if (book1.getBookTitle().equalsIgnoreCase(book.getBookTitle())) {

                if (getAvailableStock(book1) > 0) {
                    user.getArchive().addBook(book);
                    System.out.println("You have successfully borrowed the book");
                    // -1 available stock
                    changeStock(book1, -1);
                    addBorrowCount(book1);
//                    user.saveToFile();
                    saveToFile();

                } else {
                    System.out.println("Sorry, the book is currently out of stock.");
                }
            }
        }
    }
    public void saveToFile() throws IOException {
        System.out.println("Saving...");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/java/com/example/lms/userdata/_LIBRARY_ARCHIVE_.txt"));
        out.writeObject(this);
    }

}
