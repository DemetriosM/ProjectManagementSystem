package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.SkillDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.Skill;
import org.hibernate.Session;

import java.util.List;

public class SkillDaoHibernateImpl extends BaseDaoHibernateImpl<Skill> implements SkillDao {
    @Override
    public Skill read(Long id) {
        Session session = null;
        Skill skill;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            skill = session.find(Skill.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = null;
        List<Skill> skills;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            skills = session.createQuery("FROM Skill").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return skills;
    }
}
