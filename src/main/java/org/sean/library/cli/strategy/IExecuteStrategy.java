package org.sean.library.cli.strategy;

import org.sean.library.cli.cmd.BaseCmd;
import org.sean.library.constant.CliEnum;

import java.util.Map;

public interface IExecuteStrategy {

    Map<CliEnum, BaseCmd> getCliMap();

}
