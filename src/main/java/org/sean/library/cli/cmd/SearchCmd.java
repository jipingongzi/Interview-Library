package org.sean.library.cli.cmd;

import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;

public class SearchCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 2;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        String bookName = args[0].toString();
        String author = args[1].toString();
        libraryService.search(bookName, author);
    }
}
