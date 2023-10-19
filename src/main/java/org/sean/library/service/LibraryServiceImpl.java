package org.sean.library.service;

import org.sean.library.model.Book;
import org.sean.library.model.user.Admin;
import org.sean.library.model.user.Member;
import org.sean.library.util.LogUtil;

import java.util.*;

public class LibraryServiceImpl implements ILibraryService {
    private final Map<String, Book> bookMap = new LinkedHashMap<>();

    @Override
    public void addBook(String bookName, String author, Integer quantity, Admin admin) {
        if (admin == null) {
            LogUtil.log("Please login admin first.");
            return;
        }
        Book book = new Book(bookName, author, quantity);
        String key = book.getKey();
        if (bookMap.containsKey(key)) {
            Book originalBook = bookMap.get(key);
            originalBook.addInventory(book.getInventory());
            LogUtil.log(String.format("Book \"%s\" by \"%s\" updated successfully, inventory: %d.",
                    bookName, author, quantity));
        } else {
            bookMap.put(book.getKey(), book);
            LogUtil.log(String.format("Book \"%s\" by \"%s\" added successfully, inventory: %d.",
                    bookName, author, quantity));
        }
    }

    @Override
    public void deleteBook(String bookName, String author, Admin admin) {
        if (admin == null) {
            LogUtil.log("Please login admin first.");
            return;
        }
        String key = getBookKey(bookName, author);
        if (bookMap.containsKey(key)) {
            Book book = bookMap.get(key);
            if (!book.getInventory().equals(book.getAvailableInventory())) {
                LogUtil.log(String.format("Cannot delete book \"%s\" by \"%s\" it is currently borrowed.",
                        bookName, author));
            } else {
                bookMap.remove(key);
            }
        } else {
            LogUtil.log("No such book");
        }
    }

    @Override
    public List<Book> list() {
        LogUtil.log("Book List:");
        int i = 1;
        Collection<Book> books = bookMap.values();
        for (Book book : books) {
            LogUtil.log(String.format("%d. Book \"%s\" - \"%s\" - inventory: %d.",
                    i, book.getName(), book.getAuthor(), book.getAvailableInventory()));
        }
        return new ArrayList<>(books);
    }

    @Override
    public Book search(String bookName, String author) {
        String key = getBookKey(bookName, author);
        if (bookMap.containsKey(key)) {
            Book book = bookMap.get(key);
            LogUtil.log(String.format("Book \"%s\" - \"%s\" - inventory: %d.",
                    book.getName(), book.getAuthor(), book.getAvailableInventory()));
            return book;
        }
        return null;
    }

    @Override
    public void borrowBook(String bookName, String author, Member member) {
        Book book = getValidBook(bookName, author);
        if (member == null || book == null) {
            return;
        }
        Boolean memberFlag = member.borrowBook(book);
        if (memberFlag) {
            Boolean bookFlag = member.borrowBook(book);
            if(bookFlag) {
                LogUtil.log(String.format("Book %s successfully borrowed", bookName));
            }
        }
    }

    @Override
    public void returnBook(String bookName, String author, Member member) {
        Book book = getValidBook(bookName, author);
        if (member == null || book == null) {
            return;
        }
        Boolean flag = member.returnBook(book);
        if (flag) {
            book.returnBook();
            LogUtil.log(String.format("Book %s successfully returned.", bookName));
        }
    }

    private Book getValidBook(String bookName, String author) {
        String key = getBookKey(bookName, author);
        if (!bookMap.containsKey(key)) {
            LogUtil.log("No such book");
            return null;
        }
        return bookMap.get(key);
    }

    private String getBookKey(String bookName, String author) {
        return bookName + ":" + author;
    }
}
