package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Developer;

public class EditDeveloperAction extends AbstractDeveloperAction {

    @Override
    public void execute() {
        System.out.println("Select developer:");
        Developer developer = commandLine.choose(developerDao.getAll());
        System.out.println("Input developer name:");
        developer.setName(getValidString());
        System.out.println("Input developer surname:");
        developer.setSurname(getValidString());
        System.out.println("Input developer salary:");
        developer.setSalary(Integer.parseInt(getValidString().trim()));
        System.out.println("Select company.");
        Company company = commandLine.choose(companyDao.getAll());
        developer.setCompanyId(company.getId());
        developerDao.update(developer);
    }

    @Override
    public String toString() {
        return "Edit developer.";
    }
}
