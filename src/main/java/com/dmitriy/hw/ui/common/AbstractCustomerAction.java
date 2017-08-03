package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.dao.CustomerDao;
import com.dmitriy.hw.dao.impl.jdbc.module2.CustomerDaoImpl;

public abstract class AbstractCustomerAction extends ActionWithScanner {
    protected final CustomerDao customerDao;

    protected AbstractCustomerAction() {
        customerDao = new CustomerDaoImpl();
    }
}
