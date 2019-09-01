package com.lesson5;


import com.lesson5.Exception.InternalServerErrorException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public abstract class CustomDAO<T extends BaseElement> implements DAO<T> {

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    @Override
    public T save(T t) throws InternalServerErrorException {
        try (Session session = sessionFactory.openSession()
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
    public T delete(T t) throws InternalServerErrorException {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
            System.out.println("Delete item is done");
        } catch (HibernateException e) {
            throw new InternalServerErrorException("Delete is failed" + "\n" + e.getMessage());
        }
        return t;
    }

    @Override
    public T update(T t) throws InternalServerErrorException {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
            System.out.println("Update item is done");
        } catch (HibernateException e) {
            throw new InternalServerErrorException("Update item is failed" + "\n" + e.getMessage());
        }
        return t;
    }

    @Override
    public T findById(long id) throws InternalServerErrorException {
        List<T> listItem = itemsList();

        if (listItem != null) {
            for (T element : listItem) {
                if (element.getId() == id) {
                    return element;
                }
            }
        }
        return null;
    }

    public List<T> itemsList() throws InternalServerErrorException {
        List<T> resList = new ArrayList();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Item");
            resList = query.list();
            session.getTransaction().commit();
            System.out.println("get list item done");
        } catch (HibernateException e) {
            throw new InternalServerErrorException("Get list item with error" + "\n" + e.getMessage());
        }
        return resList;
    }


}
