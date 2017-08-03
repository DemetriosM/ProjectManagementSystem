package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class MainAction implements Action{

    private static final List<Action> MAIN_ACTIONS = Arrays.asList(
            new CompanyAction(),
            new DeveloperAction(),
            new SkillAction(),
            new CustomerAction(),
            new ProjectAction(),
            new ExitAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(MAIN_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Main menu";
    }


}
