package org.sean.library;

import org.sean.library.cli.InputParser;
import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.service.LibraryServiceImpl;
import org.sean.library.service.UserServiceImpl;

import java.util.Scanner;

public class LibraryApplication {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        ILibraryService libraryService = new LibraryServiceImpl();
        InputParser inputParser = new InputParser();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            inputParser.parse(input, userService, libraryService);
        }
    }
}