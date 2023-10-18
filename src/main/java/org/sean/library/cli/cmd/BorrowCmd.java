package org.sean.library.cli.cmd;

import org.sean.library.model.user.Member;
import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.util.LogUtil;

public class BorrowCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 2;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        String bookName = args[0].toString();
        String author = args[1].toString();
        Member member = (Member) userService.getCurrentUser();
        if(member == null){
            LogUtil.log("Please user login first");
            return;
        }
        libraryService.borrowBook(bookName, author, member);
    }
}
