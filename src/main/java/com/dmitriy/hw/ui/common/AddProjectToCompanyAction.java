package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Project;

public class AddProjectToCompanyAction extends AbstractCompanyAction {

    @Override
    public void execute() {
        System.out.println("Select company.");
        Company company = commandLine.choose(companyDao.getAll());
        System.out.println("Select project.");
        Project project = commandLine.choose(companyDao.getOtherProjects(company.getId()));
        companyDao.connectProject(company.getId(), project.getId());
    }

    @Override
    public String toString() {
        return "Add project to company.";
    }
}
