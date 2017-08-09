package com.dmitriy.hw.dao.impl.hibernate.module3.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtils {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private SessionUtils() {}

    public static Session getOpenSession() {
        return SESSION_FACTORY.openSession();
    }
}
