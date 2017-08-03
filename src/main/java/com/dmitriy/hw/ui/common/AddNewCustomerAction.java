package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;

public class AddNewCustomerAction extends AbstractCustomerAction {

    @Override
    public void execute() {
        System.out.println("Input customer name.");
        String customerName = getValidString();
        System.out.println("Input customer city");
        String customerCity = getValidString();
        Customer customer = new Customer(customerName, customerCity);
        customerDao.create(customer);
    }

    @Override
    public String toString() {
        return "Insert new customer.";
    }
}
