package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@Transactional
public class Repository  {

    @PersistenceContext
    private EntityManager entityManager;


    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }


//    @Override
//    public Item delete(Item item) throws InternalServerErrorException {
//        return null;
//    }
//
//    @Override
//    public Item update(Item item) throws InternalServerErrorException {
//        return null;
//    }
//
//    @Override
//    public Item findById(long id) throws InternalServerErrorException {
//        return null;
//    }


    public List<Item> listItem() {
        List<Item> items;

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Item> query = entityManager.createQuery("Select *from Items", Item.class);
            items = query.getResultList();
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

        }
        return items;
    }
}
