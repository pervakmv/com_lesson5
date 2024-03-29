package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;
import com.lesson5.Exception.ThereIsNoSuchItemException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Item save(Item item) throws InternalServerErrorException {
        try {
            entityManager.persist(item);
        } catch (EntityExistsException e) {
            throw new InternalServerErrorException("Save is failed" + "\n" + e.getMessage() + "Cause:" + e.getCause());
        }
        return item;
    }


    public Item delete(Long id) throws InternalServerErrorException, ThereIsNoSuchItemException {

        Item item = entityManager.find(Item.class, id);
        if(item==null){
            throw new ThereIsNoSuchItemException("Tehre is no such item");
        }
        try {
            entityManager.remove(item);
        } catch (EntityExistsException e) {
            throw new InternalServerErrorException("Delete item is failed" + "\n" + e.getMessage());
        }

        return item;
    }

    public Item update(Item item) throws InternalServerErrorException {
        try {
            item = entityManager.merge(item);
        } catch (EntityExistsException e) {
            throw new InternalServerErrorException("Update is failed" + "\n" + e.getMessage());
        }
        return item;
    }


    public Item findById(long id) {
        return entityManager.find(Item.class, id);
    }


    public List listItem() {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }
}
