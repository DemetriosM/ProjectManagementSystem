package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;

public class EditCustomerAction extends AbstractCustomerAction {

    @Override
    public void execute() {
        System.out.println("Select customer:");
        Customer customer = commandLine.choose(customerDao.getAll());
        System.out.println("Input customer name:");
        customer.setName(getValidString());
        System.out.println("Input customer city");
        customer.setCity(getValidString());
        customerDao.update(customer);
    }

    @Override
    public String toString() {
        return "Edit customer.";
    }
}
