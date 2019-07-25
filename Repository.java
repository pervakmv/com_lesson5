package com.lesson5;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@org.springframework.stereotype.Repository
@Transactional
public class Repository {

    @PersistenceContext
    private EntityManager entityManager;


    public Item save(Item item) {
        if (entityManager != null)
            entityManager.persist(item);
        else
            System.out.println("entity manager is null");
        //System.out.println(item);
        return item;
    }



}
