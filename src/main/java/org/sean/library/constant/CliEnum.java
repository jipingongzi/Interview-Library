package org.sean.library.constant;

import java.util.Optional;

public enum CliEnum {
    REGISTER("register"),
    LOGIN("login"),
    LIST("list"),
    SEARCH("search"),

    ADD("add"),
    DELETE("delete"),

    BORROW("borrow"),
    RETURN("return");


    private final String cmd;

    CliEnum(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public static Optional<CliEnum> findByText(String text){
        CliEnum[] cmdEnums = CliEnum.values();
        for (CliEnum cmdEnum : cmdEnums) {
            if (cmdEnum.cmd.equals(text)) {
                return Optional.of(cmdEnum);
            }
        }
        return Optional.empty();
    }
}
