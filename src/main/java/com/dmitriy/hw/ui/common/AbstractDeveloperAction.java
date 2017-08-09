package com.dmitriy.hw.ui.common;

import com.dmitriy.hw.dao.CompanyDao;
import com.dmitriy.hw.dao.DeveloperDao;
import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.dao.SkillDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.CompanyDaoHibernateImpl;
import com.dmitriy.hw.dao.impl.hibernate.module3.DeveloperDaoHibernateImpl;
import com.dmitriy.hw.dao.impl.hibernate.module3.ProjectDaoHibernateImpl;
import com.dmitriy.hw.dao.impl.hibernate.module3.SkillDaoHibernateImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.CompanyDaoImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.DeveloperDaoImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.ProjectDaoImpl;
import com.dmitriy.hw.dao.impl.jdbc.module2.SkillDaoImpl;

public abstract class AbstractDeveloperAction extends ActionWithScanner {
    protected final DeveloperDao developerDao;
    protected final CompanyDao companyDao;
    protected final SkillDao skillDao;
    protected final ProjectDao projectDao;

    protected AbstractDeveloperAction() {
        /*developerDao = new DeveloperDaoImpl();
        companyDao = new CompanyDaoImpl();
        skillDao = new SkillDaoImpl();
        projectDao = new ProjectDaoImpl(); for jdbs */

        developerDao = new DeveloperDaoHibernateImpl();
        companyDao = new CompanyDaoHibernateImpl();
        skillDao = new SkillDaoHibernateImpl();
        projectDao = new ProjectDaoHibernateImpl();
    }
}
