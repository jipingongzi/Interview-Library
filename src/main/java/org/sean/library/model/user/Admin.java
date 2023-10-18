package org.sean.library.model.user;

import org.sean.library.constant.UserType;

public class Admin extends User{
    public Admin(String userName, String pwd) {
        super(userName, pwd, UserType.ADMIN);
    }
}
