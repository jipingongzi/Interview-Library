package org.sean.library.model.user;

import org.sean.library.constant.UserType;
import org.sean.library.model.Book;
import org.sean.library.util.LogUtil;

import java.util.*;

public class Member extends User {

    public Member(String userName, String pwd) {
        super(userName, pwd, UserType.MEMBER);
    }
    private final Map<String, Book> bookMap = new HashMap<>();

    public Boolean borrowBook(Book book) {
        if(bookMap.containsKey(book.getKey())){
            LogUtil.log("You already borrowed this book.");
            return false;
        }
        bookMap.put(book.getKey(), book);
        return true;
    }

    public Boolean returnBook(Book book) {
        if(bookMap.containsKey(book.getKey())) {
            bookMap.remove(book.getKey());
            return true;
        }else {
            LogUtil.log("You are not borrow this this book");
            return false;
        }
    }
}
