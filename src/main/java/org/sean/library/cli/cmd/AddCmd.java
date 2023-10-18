package org.sean.library.cli.cmd;

import org.sean.library.model.user.Admin;
import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.util.LogUtil;

public class AddCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 3;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        String bookName = args[0].toString();
        String author = args[1].toString();
        Integer inventory = Integer.parseInt(args[2].toString());
        Admin admin = (Admin) userService.getCurrentUser();
        if(admin == null){
            LogUtil.log("Please admin login first");
            return;
        }
        libraryService.addBook(bookName, author, inventory, admin);
    }
}
