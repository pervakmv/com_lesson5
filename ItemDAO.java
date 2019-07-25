package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class ItemDAO extends CustomDAO<Item> {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Item save(Item item) {
        if (entityManager != null)
            entityManager.persist(item);
        else
            System.out.println("entity manager is null");
        //System.out.println(item);
        return item;
    }

    public List<Item> listItem() throws InternalServerErrorException {
        List<Item> resList = new ArrayList();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Item");
            resList = query.list();
            session.getTransaction().commit();
        } catch (HibernateError e) {
            throw new InternalServerErrorException("get list of Item with error");
        }
        return resList;
    }

}
