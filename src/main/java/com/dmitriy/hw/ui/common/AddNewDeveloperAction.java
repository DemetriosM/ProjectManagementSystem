package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Developer;

public class AddNewDeveloperAction extends AbstractDeveloperAction{

    @Override
    public void execute() {
        System.out.println("Input developer name:");
        String developerName = getValidString();
        System.out.println("Input developer surname:");
        String developerSurname = getValidString();
        System.out.println("Input developer salary:");
        Integer salary = Integer.parseInt(getValidString().trim());
        System.out.println("Select company.");
        Company company = commandLine.choose(companyDao.getAll());
        Developer developer = new Developer(developerSurname, developerName, salary, company.getId());
        developerDao.create(developer);
    }

    @Override
    public String toString() {
        return "Insert new developer.";
    }
}
