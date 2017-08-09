package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.DeveloperDao;
import com.dmitriy.hw.dao.ProjectDao;
import com.dmitriy.hw.dao.SkillDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.Developer;
import com.dmitriy.hw.model.Project;
import com.dmitriy.hw.model.Skill;
import org.hibernate.Session;

import java.util.List;

public class DeveloperDaoHibernateImpl extends BaseDaoHibernateImpl<Developer> implements DeveloperDao {
    private SkillDao skillDaoHibernate;
    private ProjectDao projectDaoHibernate;

    public DeveloperDaoHibernateImpl() {
        skillDaoHibernate = new SkillDaoHibernateImpl();
        projectDaoHibernate = new ProjectDaoHibernateImpl();
    }

    @Override
    public Developer read(Long id) {
        Session session = null;
        Developer developer;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            developer = (Developer) session.createQuery("SELECT d FROM Developer d JOIN FETCH d.projects").list().get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = null;
        List<Developer> developers;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            developers = session.createQuery("FROM Developer").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return developers;
    }

    @Override
    public List<Skill> getDeveloperSkills(Long developerId) {
        Developer developer = read(developerId);
        if (developer != null) return developer.getSkills();
        return null;
    }

    @Override
    public boolean addSkill(Long developerId, Long skillId) {
        Developer developer = read(developerId);
        Skill skill = skillDaoHibernate.read(skillId);
        boolean flag = false;
        if (skill != null && developer != null) {
            if (!developer.getSkills().contains(skill)) {
                flag = developer.getSkills().add(skill);
                update(developer);
            }
        }
        return flag;
    }

    @Override
    public boolean removeSkill(Long developerId, Long skillId) {
        Developer developer = read(developerId);
        boolean flag = false;
        if (developer != null) {
            flag = developer.getSkills().removeIf(skill -> skill.getId().equals(skillId));
            update(developer);
        }
        return flag;
    }

    @Override
    public List<Project> getDeveloperProjects(Long developerId) {
        Developer developer = read(developerId);
        if (developer != null) return developer.getProjects();
        return null;
    }

    @Override
    public boolean connectToProject(Long developerId, Long projectId) {
        Developer developer = read(developerId);
        Project project = projectDaoHibernate.read(projectId);
        boolean flag = false;
        if (project != null && developer != null) {
            if (!developer.getProjects().contains(project)) {
                flag = developer.getProjects().add(project);
                update(developer);
            }
        }
        return flag;
    }

    @Override
    public boolean disconnectFromProject(Long developerId, Long projectId) {
        Developer developer = read(developerId);
        boolean flag = false;
        if (developer != null) {
            flag = developer.getProjects().removeIf(project -> project.getId().equals(projectId));
            update(developer);
        }
        return flag;
    }
}
