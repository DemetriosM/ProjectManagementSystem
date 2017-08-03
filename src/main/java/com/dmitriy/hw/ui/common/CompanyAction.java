package com.dmitriy.hw.ui.common;

import java.util.Arrays;
import java.util.List;

public class CompanyAction implements Action {
    private static final List<Action> COMPANY_ACTIONS = Arrays.asList(
            new DisplayCompaniesAction(),
            new AddNewCompanyAction(),
            new EditCompanyAction(),
            new RemoveCompanyAction(),
            new AddProjectToCompanyAction(),
            new DisconnectProjectFromCompany(),
            new MainAction());

    @Override
    public void execute(CommandLine commandLine) {
        Action action = commandLine.choose(COMPANY_ACTIONS);
        action.execute(commandLine);
    }

    @Override
    public String toString() {
        return "Select company action.";
    }
}
