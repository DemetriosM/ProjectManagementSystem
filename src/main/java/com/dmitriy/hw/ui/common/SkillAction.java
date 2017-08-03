package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class SkillAction implements Action {
    private static final List<Action> SKILL_ACTIONS = Arrays.asList(
            new DisplaySkillsAction(),
            new AddNewSkillAction(),
            new EditSkillAction(),
            new RemoveSkillAction(),
            new MainAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(SKILL_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Select skills action.";
    }
}
