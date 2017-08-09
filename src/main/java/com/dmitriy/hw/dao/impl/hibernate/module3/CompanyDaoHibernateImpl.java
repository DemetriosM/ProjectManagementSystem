package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.CompanyDao;
import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.Company;
import com.dmitriy.hw.model.Project;
import org.hibernate.Session;

import java.util.List;

public class CompanyDaoHibernateImpl extends BaseDaoHibernateImpl<Company> implements CompanyDao {
    private ProjectDao projectDaoHibernate;

    public CompanyDaoHibernateImpl() {
        projectDaoHibernate = new ProjectDaoHibernateImpl();
    }

    @Override
    public Company read(Long id) {
        Session session = null;
        Company company;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            company = (Company) session.createQuery("SELECT c FROM Company c JOIN FETCH c.projects").list().get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session session = null;
        List<Company> companies;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            companies = session.createQuery("FROM Company").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return companies;
    }

    @Override
    public List<Project> getCompanyProjects(Long companyId) {
        Company company = read(companyId);
        if (company != null) {
            return company.getProjects();
        }
        return null;
    }

    @Override
    public List<Project> getOtherProjects(Long companyId) {
        List<Project> projects = projectDaoHibernate.getAll();
        List<Project> companyProjects = getCompanyProjects(companyId);
        if (companyProjects != null) projects.removeAll(companyProjects);
        return projects;
    }

    @Override
    public boolean connectProject(Long companyId, Long projectId) {
        Company company = read(companyId);
        Project project = projectDaoHibernate.read(projectId);
        boolean flag = false;
        if (project != null && company != null) {
            flag = company.getProjects().add(project);
            update(company);
        }
        return flag;
    }

    @Override
    public boolean disconnectProject(Long companyId, Long projectId) {
        Company company = read(companyId);
        boolean flag = false;
        if (company != null) {
            flag = company.getProjects().removeIf(project -> project.getId().equals(projectId));
            update(company);
        }
        return flag;
    }
}
