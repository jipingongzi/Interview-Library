package org.sean.library.cli.cmd;

import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;

public class ListCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 0;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        libraryService.list();
    }
}
