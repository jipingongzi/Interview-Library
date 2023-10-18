package org.sean.library.cli.cmd;

import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;

public class LoginCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 2;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        String userName = args[0].toString();
        String pwd = args[1].toString();
        userService.login(userName, pwd);
    }
}
