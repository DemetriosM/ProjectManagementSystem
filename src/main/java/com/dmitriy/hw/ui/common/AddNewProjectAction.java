package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;
import com.dmitriy.hw.model.Project;

public class AddNewProjectAction extends AbstractProjectAction{

    @Override
    public void execute() {
        System.out.println("Input project name:");
        String projectName = getValidString();
        System.out.println("Input project cost:");
        Integer companyCost = Integer.parseInt(getValidString().trim());
        System.out.println("Select customer:");
        Customer customer = commandLine.choose(customerDao.getAll());
        //Project project = new Project(projectName, customer.getId(), companyCost); for jdbc
        Project project = new Project(projectName, customer, companyCost);
        projectDao.create(project);
    }

    @Override
    public String toString() {
        return "Insert new project.";
    }
}
