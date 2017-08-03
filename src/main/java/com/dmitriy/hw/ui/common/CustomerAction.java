package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class CustomerAction implements Action {
    private static final List<Action> CUSTOMER_ACTIONS = Arrays.asList(
            new DisplayCustomersAction(),
            new AddNewCustomerAction(),
            new EditCustomerAction(),
            new RemoveCustomerAction(),
            new MainAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(CUSTOMER_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Select customer action.";
    }
}
