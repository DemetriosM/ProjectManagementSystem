package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.dao.CompanyDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.CompanyDaoHibernateImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.CompanyDaoImpl;

public abstract class AbstractCompanyAction extends ActionWithScanner {
    protected final CompanyDao companyDao;

    protected AbstractCompanyAction() {
        //companyDao = new CompanyDaoImpl(); - for jdbc
        companyDao = new CompanyDaoHibernateImpl();
    }
}
