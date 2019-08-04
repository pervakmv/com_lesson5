package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }


    public Item delete(Long id) {
        Item item = entityManager.find(Item.class, id);
        entityManager.remove(item);
        return item;
    }

    public Item update(Item item) {
        item = entityManager.merge(item);
        return item;
    }


    public Item findById(long id) {
        return entityManager.find(Item.class, id);
    }


    public List listItem() {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }
}
