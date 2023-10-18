package org.sean.library.constant;

import java.util.Optional;

public enum UserType {
    MEMBER("user"), ADMIN("admin");

    private final String text;

    UserType(String text) {
        this.text = text;
    }

    public static Optional<UserType> findByText(String text){
        UserType[] userTypes = UserType.values();
        for (UserType userType : userTypes) {
            if (userType.text.equals(text)) {
                return Optional.of(userType);
            }
        }
        return Optional.empty();
    }
}
