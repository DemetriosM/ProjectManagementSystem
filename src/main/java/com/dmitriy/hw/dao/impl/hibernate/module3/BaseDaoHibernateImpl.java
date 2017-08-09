package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.BaseDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.BaseModel;
import org.hibernate.Session;

import java.util.List;

public abstract class BaseDaoHibernateImpl<T extends BaseModel> implements BaseDao<T> {
    @Override
    public T create(T item) {
        Session session = null;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            Long id = (Long)session.save(item);
            session.getTransaction().commit();
            item.setId(id);
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return item;
    }

    @Override
    public T update(T item) {
            Session session = null;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            item = (T)session.merge(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return item;
    }

    @Override
    public boolean delete(Long id) {
        Session session = null;
        boolean flag = false;
        try {
            T item = this.read(id);
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            session.remove(item);
            flag = session.contains(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return flag;
    }

    @Override
    public abstract T read(Long id);

    @Override
    public abstract List<T> getAll();
}
