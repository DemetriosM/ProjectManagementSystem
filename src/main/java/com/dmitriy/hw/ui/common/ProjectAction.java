package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class ProjectAction implements Action{
    private static final List<Action> PROJECT_ACTIONS = Arrays.asList(
            new DisplayProjectsAction(),
            new AddNewProjectAction(),
            new EditProjectAction(),
            new RemoveProjectAction(),
            new MainAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(PROJECT_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Select project action.";
    }
}
