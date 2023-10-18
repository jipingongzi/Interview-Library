package org.sean.library.cli.cmd;

import org.sean.library.constant.UserType;
import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.util.LogUtil;

import java.util.Optional;

public class RegisterCmd extends BaseCmd{
    @Override
    protected int argSize() {
        return 3;
    }

    @Override
    protected void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args) {
        String userTypeStr = args[0].toString();
        Optional<UserType> userTypeOptional = UserType.findByText(userTypeStr);
        if(!userTypeOptional.isPresent()){
            LogUtil.log("No such user type.");
            return;
        }
        String userName = args[1].toString();
        String pwd = args[2].toString();
        userService.register(userName, pwd, userTypeOptional.get());
    }
}
