package com.study.demo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSession();

    private static SessionFactory buildSession() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Error al iniciar Hibernate: " + ex);
        }
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }
}
