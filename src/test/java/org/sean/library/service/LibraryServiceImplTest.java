package org.sean.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sean.library.constant.UserType;
import org.sean.library.model.Book;
import org.sean.library.model.user.Admin;
import org.sean.library.model.user.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryServiceImplTest {

    private IUserService userService;
    private ILibraryService libraryService;
    private String adminName = "admin";
    private String adminPwd = "123";
    private String memberName = "member";
    private String memberPwd = "123";

    @BeforeEach
    public void init(){
        userService = new UserServiceImpl();
        libraryService = new LibraryServiceImpl();
        registerUser();
    }

    @Test
    void addBook_notExist_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        List<Book> books = libraryService.list();
        assertEquals(1, books.size());
        assertEquals(bookName, books.get(0).getName());
    }

    @Test
    void addBook_exist_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        addBook(bookName, author, inventory);
        List<Book> books = libraryService.list();
        assertEquals(1, books.size());
        assertEquals(inventory * 2, books.get(0).getInventory());
    }

    @Test
    void deleteBook_exist_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        List<Book> books = libraryService.list();
        assertEquals(1, books.size());

        libraryService.deleteBook(bookName, author, (Admin) userService.getCurrentUser());
        books = libraryService.list();
        assertEquals(0, books.size());
    }


    @Test
    void search_exist_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        Book book = libraryService.search(bookName, author);
        assertEquals(bookName, book.getName());
        assertEquals(author, book.getAuthor());
    }

    @Test
    void borrowBook_inventoryEnough_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        Member member = memberLogin();
        libraryService.borrowBook(bookName, author, member);
        Book book = libraryService.search(bookName, author);
        assertEquals(inventory - 1, book.getAvailableInventory());
    }

    @Test
    void returnBook_borrowed_success() {
        String bookName = "clean code";
        String author = "MF";
        int inventory = 10;
        addBook(bookName, author, inventory);
        Member member = memberLogin();
        libraryService.borrowBook(bookName, author, member);
        Book book = libraryService.search(bookName, author);
        assertEquals(inventory - 1, book.getAvailableInventory());

        libraryService.returnBook(bookName, author, member);
        assertEquals(inventory, book.getAvailableInventory());
    }

    private void registerUser(){
        userService.register(adminName, adminPwd, UserType.ADMIN);
        userService.register(memberName, memberPwd, UserType.MEMBER);
    }
    private Admin adminLogin(){
        return (Admin) userService.login(adminName, adminPwd);
    }
    private Member memberLogin(){
        return (Member) userService.login(memberName, memberPwd);
    }

    private void addBook(String bookName, String author, Integer inventory){
        Admin admin = adminLogin();
        libraryService.addBook(bookName, author, inventory, admin);
    }
}