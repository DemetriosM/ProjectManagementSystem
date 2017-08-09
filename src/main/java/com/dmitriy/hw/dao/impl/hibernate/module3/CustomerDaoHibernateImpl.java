package com.dmitriy.hw.dao.impl.hibernate.module3;

import com.dmitriy.hw.dao.CustomerDao;
import com.dmitriy.hw.dao.impl.hibernate.module3.utils.SessionUtils;
import com.dmitriy.hw.model.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerDaoHibernateImpl extends BaseDaoHibernateImpl<Customer> implements CustomerDao {
    @Override
    public Customer read(Long id) {
        Session session = null;
        Customer customer;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            customer = session.find(Customer.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = null;
        List<Customer> customers;
        try {
            session = SessionUtils.getOpenSession();
            session.beginTransaction();
            customers = session.createQuery("FROM Customer").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return customers;
    }
}
