package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;

public class AddNewCompanyAction extends AbstractCompanyAction {

    @Override
    public void execute() {
        System.out.println("Input company name:");
        String companyName = getValidString();
        System.out.println("Input company city:");
        String companyCity = getValidString();
        Company company = new Company(companyName, companyCity);
        companyDao.create(company);
    }

    @Override
    public String toString() {
        return "Insert new company.";
    }
}
