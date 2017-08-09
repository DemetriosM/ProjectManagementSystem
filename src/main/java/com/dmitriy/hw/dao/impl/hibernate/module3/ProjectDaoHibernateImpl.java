package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.Project;
import org.hibernate.Session;

import java.util.List;

public class ProjectDaoHibernateImpl extends BaseDaoHibernateImpl<Project> implements ProjectDao {
    @Override
    public Project read(Long id) {
        Session session = null;
        Project project;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            project = session.find(Project.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return project;
    }

    @Override
    public List<Project> getAll() {
        Session session = null;
        List<Project> projects;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            projects = session.createQuery("FROM Project").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return projects;
    }
}
