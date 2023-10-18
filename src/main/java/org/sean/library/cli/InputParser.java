package org.sean.library.cli;

import org.sean.library.cli.strategy.AdminStrategy;
import org.sean.library.cli.strategy.IExecuteStrategy;
import org.sean.library.cli.strategy.MemberStrategy;
import org.sean.library.constant.CliEnum;
import org.sean.library.constant.UserType;
import org.sean.library.model.user.User;
import org.sean.library.service.ILibraryService;
import org.sean.library.service.IUserService;
import org.sean.library.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputParser {

    private final MemberStrategy memberStrategy = new MemberStrategy();
    private final AdminStrategy adminStrategy = new AdminStrategy();
    private IExecuteStrategy executeStrategy = adminStrategy;

    public void parse(String input, IUserService userService, ILibraryService libraryService){
        String[] items = parseString(input);
        String prefix = items[0];
        Optional<CliEnum> cliOptional = CliEnum.findByText(prefix);
        if (!cliOptional.isPresent()) {
            LogUtil.log("No such cmd");
            return;
        }
        CliEnum cli = cliOptional.get();
        if(!executeStrategy.getCliMap().containsKey(cli)){
            LogUtil.log("No such cmd for current user.");
            return;
        }
        Object[] args = new Object[items.length - 1];
        System.arraycopy(items, 1, args, 0, items.length - 1);
        try {
            executeStrategy.getCliMap().get(cli).execute(userService, libraryService, args);
        } catch (Exception e){
            LogUtil.log(e.getMessage());
        }
        switchStrategy(cli, userService);
    }

    private void switchStrategy(CliEnum cli, IUserService userService){
        User currentUser = userService.getCurrentUser();
        if(CliEnum.LOGIN.equals(cli) && currentUser != null){
            if(UserType.MEMBER.equals(currentUser.getType())){
                this.executeStrategy = memberStrategy;
            }else {
                this.executeStrategy = adminStrategy;
            }
        }
    }

    private String[] parseString(String input) {
        if (!input.contains("\"")) {
            return input.split(" ");
        } else {
            List<String> tokens = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean insideQuotes = false;
            for (char c : input.toCharArray()) {
                if (c == '\"') {
                    insideQuotes = !insideQuotes;
                    if (!insideQuotes) {
                        tokens.add(sb.toString().trim());
                        sb.setLength(0);
                    }
                } else if (!insideQuotes && c == ' ') {
                    tokens.add(sb.toString().trim());
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                tokens.add(sb.toString().trim());
            }
            return tokens.stream().filter(t -> t.length() > 0).toArray(String[]::new);
        }
    }
}
