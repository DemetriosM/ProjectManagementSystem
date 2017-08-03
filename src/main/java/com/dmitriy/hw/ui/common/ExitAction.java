package com.dmitriy.hw.ui.common;

public class ExitAction implements Action {

    @Override
    public void execute(CommandLine commandLine) {
        System.exit(0);
    }

    @Override
    public String toString() {
        return "Exit";
    }
}
