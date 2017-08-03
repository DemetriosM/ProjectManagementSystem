package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.dao.CustomerDao;
import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.dao.impl.jdbc.module2.CustomerDaoImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.ProjectDaoImpl;

public abstract class AbstractProjectAction extends ActionWithScanner{
    protected final ProjectDao projectDao;
    protected final CustomerDao customerDao;

    protected AbstractProjectAction() {
        projectDao = new ProjectDaoImpl();
        customerDao = new CustomerDaoImpl();
    }
}
