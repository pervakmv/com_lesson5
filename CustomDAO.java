package com.lesson5;


import com.lesson5.Exception.InternalServerErrorException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CustomDAO<T> implements DAO<T> {

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    @Override
    public T save(T t) throws InternalServerErrorException {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()
        ) {

            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            System.out.println("Save item is done");

        } catch (HibernateException e) {
            throw new InternalServerErrorException("Save is failed" + "\n" + e.getMessage() + "Cause:" + e.getCause());
        }
        return t;
    }




    @Override
    public T delete(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public T findById(long id) {
        return null;
    }


}
