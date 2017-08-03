package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;

public class EditCompanyAction extends AbstractCompanyAction {

    @Override
    public void execute() {
        System.out.println("Select company:");
        Company company = commandLine.choose(companyDao.getAll());
        System.out.println("Input new name name:");
        company.setName(getValidString());
        System.out.println("Input new company city:");
        company.setCity(getValidString());
        companyDao.update(company);
    }

    @Override
    public String toString() {
        return "Edit company.";
    }
}
