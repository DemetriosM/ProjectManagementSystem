package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;

import java.util.List;

public class DisplayCompaniesAction extends AbstractCompanyAction {

    @Override
    public void execute() {
        List<Company> companies = companyDao.getAll();
        for (Company company: companies) {
            System.out.println(company);
        }
    }

    @Override
    public String toString() {
        return "Display all companies";
    }
}
