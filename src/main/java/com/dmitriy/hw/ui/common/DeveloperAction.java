package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class DeveloperAction implements Action {
    private static final List<Action> DEVELOPER_ACTIONS = Arrays.asList(
            new DisplayDevelopersAction(),
            new AddNewDeveloperAction(),
            new AddSkillToDeveloper(),
            new RemoveSkillFromDeveloper(),
            new AddDeveloperToProject(),
            new RemoveDeveloperFromProject(),
            new EditDeveloperAction(),
            new RemoveDeveloperAction(),
            new MainAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(DEVELOPER_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Select developer action.";
    }
}
