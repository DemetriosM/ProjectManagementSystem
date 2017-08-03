package com.dmitriy.hw.ui;

import com.dmitriy.hw.ui.common.CommandLine;
import com.dmitriy.hw.ui.common.MainAction;

public class Main {
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        MainAction mainAction = new MainAction();

        while (true){
            mainAction.execute(commandLine);
        }

    }
}
