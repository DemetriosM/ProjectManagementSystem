package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;
import com.dmitriy.hw.model.Project;

public class EditProjectAction extends AbstractProjectAction{

    @Override
    public void execute() {
        System.out.println("Select project:");
        Project project = commandLine.choose(projectDao.getAll());
        System.out.println("Input project name:");
        project.setName(getValidString());
        System.out.println("Input project cost:");
        project.setCost(Integer.parseInt(getValidString().trim()));
        System.out.println("Select customer:");
        Customer customer = commandLine.choose(customerDao.getAll());
        project.setCustomer(customer);
        project.setCustomerId(customer.getId());
        projectDao.update(project);
    }

    @Override
    public String toString() {
        return "Edit project.";
    }
}
