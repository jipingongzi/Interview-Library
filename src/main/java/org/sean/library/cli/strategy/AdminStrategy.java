package org.sean.library.cli.strategy;

import org.sean.library.cli.cmd.*;
import org.sean.library.constant.CliEnum;

import java.util.HashMap;
import java.util.Map;

public class AdminStrategy implements IExecuteStrategy {

    private final Map<CliEnum, BaseCmd> cmdMap = new HashMap<>();

    public AdminStrategy() {
        cmdMap.put(CliEnum.REGISTER, new RegisterCmd());
        cmdMap.put(CliEnum.LOGIN, new LoginCmd());
        cmdMap.put(CliEnum.LIST, new ListCmd());
        cmdMap.put(CliEnum.SEARCH, new SearchCmd());
        cmdMap.put(CliEnum.ADD, new AddCmd());
        cmdMap.put(CliEnum.DELETE, new DeleteCmd());
    }

    @Override
    public Map<CliEnum, BaseCmd> getCliMap() {
        return cmdMap;
    }
}
