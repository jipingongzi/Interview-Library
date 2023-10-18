package org.sean.library.service;

import org.sean.library.constant.UserType;
import org.sean.library.model.user.Admin;
import org.sean.library.model.user.Member;
import org.sean.library.model.user.User;
import org.sean.library.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements IUserService {

    private User currentUser = null;
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public Boolean login(String userName, String pwd) {
        if (!userMap.containsKey(userName)) {
            LogUtil.log("User not exist");
            return false;
        }
        User user = userMap.get(userName);
        String originalPwd = user.getPwd();
        if (pwd.equals(originalPwd)) {
            if (UserType.MEMBER.equals(user.getType())) {
                currentUser = user;
                LogUtil.log(String.format("User %s successfully logged in.", userName));
            } else {
                currentUser = user;
                LogUtil.log(String.format("Admin %s successfully logged in.", userName));
            }
            return true;
        } else {
            LogUtil.log("User name or pwd incorrect.");
            return false;
        }
    }

    @Override
    public void register(String userName, String pwd, UserType userType) {
        if(userMap.containsKey(userName)){
            LogUtil.log(String.format("User name: %s already exist.", userName));
            return;
        }
        User user;
        if (UserType.MEMBER.equals(userType)) {
            user = new Member(userName, pwd);
            LogUtil.log(String.format("User %s successfully registered.", userName));
        } else {
            user = new Admin(userName, pwd);
            LogUtil.log(String.format("Admin %s successfully registered.", userName));
        }
        userMap.put(userName, user);
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
