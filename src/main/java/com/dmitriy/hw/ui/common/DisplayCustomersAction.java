package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;

import java.util.List;

public class DisplayCustomersAction extends AbstractCustomerAction {
    @Override
    public void execute() {
        List<Customer> customers = customerDao.getAll();
        for(Customer customer: customers) {
            System.out.println(customer);
        }
    }

    @Override
    public String toString() {
        return "Display all customers";
    }
}
