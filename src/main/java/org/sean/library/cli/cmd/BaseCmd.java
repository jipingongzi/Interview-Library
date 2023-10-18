package org.sean.library.cli.cmd;

import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.util.LogUtil;

public abstract class BaseCmd {

    protected boolean validArgs(Object[] args) {
        if (argSize() == 0) {
            return true;
        } else if (args == null || args.length == 0) {
            LogUtil.log("No required args");
            return false;
        } else if (argSize() != args.length) {
            LogUtil.log("Invalid args");
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return min arg numbers
     */
    protected abstract int argSize();

    protected abstract void cmdExecute(IUserService userService, ILibraryService libraryService, Object[] args);

    public void execute(IUserService userService, ILibraryService libraryService, Object[] args) {
        if (validArgs(args)) {
            cmdExecute(userService, libraryService, args);
        }
    }
}
