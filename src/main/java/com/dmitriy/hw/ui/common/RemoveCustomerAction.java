package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.model.Customer;

public class RemoveCustomerAction extends AbstractCustomerAction {

    @Override
    public void execute() {
        System.out.println("Select customer:");
        Customer customer = commandLine.choose(customerDao.getAll());
        customerDao.delete(customer.getId());
    }

    @Override
    public String toString() {
        return "Remove customer.(Warning! Projects that are assigned to this user will also be deleted.)";
    }
}
