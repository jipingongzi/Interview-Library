package org.sean.library.model.user;

import org.sean.library.constant.UserType;
import org.sean.library.model.Book;

import java.util.*;

public class Member extends User {

    public Member(String userName, String pwd) {
        super(userName, pwd, UserType.MEMBER);
    }
    private final Map<String, Book> bookMap = new HashMap<>();

    public void borrowBook(Book book) {
        bookMap.put(book.getKey(), book);
    }

    public void returnBook(Book book) {
        bookMap.remove(book.getKey());
    }
}
