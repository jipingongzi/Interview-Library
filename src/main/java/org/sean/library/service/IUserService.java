package org.sean.library.service;

import org.sean.library.constant.UserType;
import org.sean.library.model.user.User;

public interface IUserService {

    User login(String userName, String pwd);

    void register(String userName, String pwd, UserType type);

    User getCurrentUser();

}
