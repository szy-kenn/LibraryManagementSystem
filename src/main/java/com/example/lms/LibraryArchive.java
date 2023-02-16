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

    Random random = new Random();

    public List<User> getUsersList() {
        return usersList;
    }

    public int booksReturnedCount;

    public LibraryArchive () {
        usersList = new ArrayList<>();
        booksList = new HashMap<>();
        List<Book> books = new ArrayList<>();
        books.add(new Book("THE FAULT IN OUR STARS", "JOHN GREEN", "YOUNG ADULT"));
        books.add(new Book("THE SILENT PATIENT", "ALEX MICHAELIDES", "MYSTERY"));
        books.add(new Book("ALICE'S ADVENTURES IN WONDERLAND", "LEWIS CARROLL", "KIDS"));
        books.add(new Book("ANNE OF GREEN GABLES", "L.M. MONTGOMERY", "KIDS"));
        books.add(new Book("FIFTY SHADES OF GREY", "E.L. JAMES", "ROMANCE"));
        books.add(new Book("HARRY POTTER AND THE SORCERER'S STONE", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HOWL'S MOVING CASTLE", "DIANA WYNNE JONES", "FANTASY"));
        books.add(new Book("THE DA VINCI CODE", "DAN BROWN", "MYSTERY"));
        books.add(new Book("THE HOBBIT", "J.R.R. TOLKIEN", "FANTASY"));
        books.add(new Book("THE LITTLE PRINCE", "ANTOINE DE SAINT-EXUPÉRY", "KIDS"));
        books.add(new Book("TO ALL THE BOYS I'VE LOVED BEFORE", "JENNY HAN", "ROMANCE"));
        books.add(new Book("WHERE THE CRAWDADS SING", "DELIA OWENS", "MYSTERY"));
        books.add(new Book("BRIDGE TO TERABITHIA", "KATHERINE PATERSON", "KIDS"));
        books.add(new Book("CHARLIE AND THE CHOCOLATE FACTORY", "ROALD DAHL", "KIDS"));
        books.add(new Book("THE SONG OF ACHILLES", "MADELINE MILLER", "ROMANCE"));
        books.add(new Book("PRIDE & PREJUDICE", "JANE AUSTEN", "ROMANCE"));
        books.add(new Book("THE MAID A NOVEL", "NITA PROSE", "MYSTERY"));
        books.add(new Book("ONE OF US IS LYING", "KAREN M. MCMANUS", "MYSTERY"));
        books.add(new Book("THE SEVEN DEATHS OF EVELYN HARDCASTLE", "STUART TURTON", "MYSTERY"));
        books.add(new Book("A GAME OF THRONES", "GEORGE. R. R. MARTIN", "FANTASY"));
        books.add(new Book("THE LION THE WITCH AND THE WARDROBE", "C.S. LEWIS", "FANTASY"));
        books.add(new Book("THE DIARY OF A YOUNG GIRL", "ANNE FRANK", "HISTORY"));
        books.add(new Book("THE ART OF WAR", "SUN TZU", "HISTORY"));
        books.add(new Book("SAPIENS A BRIEF HISTORY OF HUMANKIND", "YUVAL NOAH HARARI", "NONFICTION"));
        books.add(new Book("EUROPE A HISTORY", "NORMAN DAVIES", "HISTORY"));
        books.add(new Book("THE RAPE OF NANKING", "IRIS CHANG", "HISTORY"));
        books.add(new Book("THE GIRL ON THE TRAIN", "PAULA HAWKINS", "MYSTERY"));
        books.add(new Book("VERITY", "COLLEEN HOOVER", "MYSTERY"));
        books.add(new Book("THE POSTMAN ALWAYS RINGS TWICE", "JAMES M. CAIN", "MYSTERY"));
        books.add(new Book("GONE GIRL", "GILLIAN FLYNN", "MYSTERY"));
        books.add(new Book("THE NOTEBOOK", "NICHOLAS SPARKS", "ROMANCE"));
        books.add(new Book("THE DUKE AND I", "JULIA QUINN", "ROMANCE"));
        books.add(new Book("LOVE ON THE BRAIN", "ALI HAZELWOOD", "ROMANCE"));
        books.add(new Book("I LOVE YOU TO THE MOON AND BACK", "TIM WARNES", "KIDS"));
        books.add(new Book("HARRY POTTER AND THE CHAMBER OF SECRETS", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HARRY POTTER AND THE PRISONER OF AZKABAN", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HARRY POTTER AND THE GOBLET OF FIRE", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HARRY POTTER AND THE ORDER OF THE PHOENIX", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HARRY POTTER AND THE HALF BLOOD PRINCE", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("HARRY POTTER AND THE DEATHLY HALLOWS", "J.K. ROWLING", "FANTASY"));
        books.add(new Book("THE BROMANCE BOOK CLUB", "LYSSA KAY ADAMS", "ROMANCE"));
        books.add(new Book("THE ART OF LOVING", "ERICH FROMM", "NONFICTION"));
        books.add(new Book("TUESDAYS WITH MORRIE", "MITCH ALBOM", "BIOGRAPHY"));
        books.add(new Book("THEY BOTH DIE AT THE END", "ADAM SILVERA", "YOUNG ADULT"));
        books.add(new Book("WHAT IF IT'S US", "BECKY ALBERTALLI & ADAM SILVERA", "ROMANCE"));
        books.add(new Book("BEHIND HER EYES", "SARAH PINBOROUGH", "MYSTERY"));
        books.add(new Book("LAYLA", "COLLEEN HOOVER", "MYSTERY"));
        books.add(new Book("PEDRO'S YOYOS", "ROBERTO PENAS", "NONFICTION"));
        books.add(new Book("EINSTEIN HIS LIFE AND UNIVERSE", "WALTER ISAACSON", "BIOGRAPHY"));
        books.add(new Book("ALAN TURING THE ENIGMA", "ANDREW HODGES", "BIOGRAPHY"));
        books.add(new Book("THE OMNIVORE'S DILEMMA", "MICHAEL POLLAN", "NONFICTION"));
        books.add(new Book("A BRIEF HISTORY OF TIME", "STEPHEN HAWKING", "NONFICTION"));
        books.add(new Book("THE HUNGER GAMES", "SUZANNE COLLINS", "YOUNG ADULT"));
        books.add(new Book("A GOOD GIRL'S GUIDE TO MURDER", "HOLLY JACKSON", "YOUNG ADULT"));
        books.add(new Book("IT'S KIND OF A FUNNY STORY", "NED VIZZINI", "YOUNG ADULT"));
        books.add(new Book("DRACULA", "BRAM STOKER", "HORROR"));
        books.add(new Book("BIRD BOX", "JOSH MALERMAN", "HORROR"));
        books.add(new Book("IT", "STEPHEN KING", "HORROR"));
        books.add(new Book("MEXICAN GOTHIC", "SILVIA MORENO-GARCIA", "HORROR"));
        books.add(new Book("CORALINE", "NEILS GAIMAN", "HORROR"));

        for (Book book : books) {
            int randomNum = random.nextInt(1, 20);
            booksList.put(book, new ArrayList<>(Arrays.asList(randomNum, 0)));
        }
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
        booksList.put(book, new ArrayList<>(Arrays.asList(stock, 0)));
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
