package com.diploma.Utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Session session;

    private static SessionFactory buildSessionFactory() {
        System.out.println("HibernateUtil initialize");
        try{
            if (sessionFactory == null)
            {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");

                sessionFactory = configuration.buildSessionFactory();
            }
            openSession();
            return sessionFactory;
        }catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static void openSession() {
        if(session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
        }
    }

    public static Session getSession() throws HibernateException {
        return session;
    }

    public static void shutdown() {
        getSession().close();
    }
}