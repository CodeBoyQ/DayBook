package com.codeboyq.daybook.api.dao;

import com.codeboyq.daybook.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class ItemDao implements IItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Item> getAllItems() {
        String query = "FROM Item ORDER BY id";
        return (List<Item>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Item getItemById(int itemId) {
        return entityManager.find(Item.class, itemId);
    }

    @Override
    public Item addItem(Item item) {
        entityManager.persist(item);
        entityManager.flush();
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        Item uItem = getItemById(item.getId());
        uItem.setDate(item.getDate());
        uItem.setText(item.getText());
        uItem.setPublished(item.getPublished());
        entityManager.flush();
        return uItem;
    }

    @Override
    public void deleteItem(int id) {
        entityManager.remove(getItemById(id));
    }

    @Override
    public boolean itemExists(LocalDate datum) {
        String query = "FROM Item WHERE date = ?";
        int count = entityManager.createQuery(query).setParameter(0, datum).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public boolean itemExists(LocalDate datum, int excludedId) {
        String query = "FROM Item WHERE date = ? and id <> ?";
        int count = entityManager.createQuery(query).setParameter(0, datum).setParameter(1, excludedId).getResultList().size();
        return count > 0 ? true : false;
    }
}