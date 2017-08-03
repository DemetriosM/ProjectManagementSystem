package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;

public class RemoveCompanyAction extends AbstractCompanyAction {

    @Override
    public void execute() {
        System.out.println("Select company:");
        Company company = commandLine.choose(companyDao.getAll());
        companyDao.delete(company.getId());
    }

    @Override
    public String toString() {
        return "Remove company. (Warning! The developers assigned to this company will also be deleted.)";
    }
}
