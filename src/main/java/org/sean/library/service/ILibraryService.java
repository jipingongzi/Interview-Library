package org.sean.library.service;

import org.sean.library.model.Book;
import org.sean.library.model.user.Admin;
import org.sean.library.model.user.Member;

import java.util.List;

public interface ILibraryService {

    void addBook(String bookName, String author, Integer quantity, Admin admin);

    void deleteBook(String bookName, String author, Admin admin);

    List<Book> list();

    Book search(String bookName, String author);

    void borrowBook(String bookName, String author, Member member);

    void returnBook(String bookName, String author, Member member);
}
