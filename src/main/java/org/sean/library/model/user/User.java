package org.sean.library.model.user;

import org.sean.library.constant.UserType;

public abstract class User {

    protected String userName;
    protected String pwd;
    protected UserType type;

    public User(String userName, String pwd, UserType type) {
        this.userName = userName;
        this.pwd = pwd;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public String getPwd() {
        return pwd;
    }

    public UserType getType() {
        return type;
    }
}
