package com.codeboyq.daybook.api.dao;

import com.codeboyq.daybook.entity.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class ItemDao implements IItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
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
    public void addItem(Item item) {
        entityManager.persist(item);
    }

    @Override
    public void updateItem(Item item) {
        Item uItem = getItemById(item.getId());
        uItem.setDate(item.getDate());
        uItem.setText(item.getText());
        entityManager.flush();
    }

    @Override
    public void deleteItem(int id) {
        entityManager.remove(getItemById(id));
    }

    @Override
    public boolean itemExists(Date datum) {
        String query = "FROM Item WHERE date = ?";
        int count = entityManager.createQuery(query).setParameter(0, datum).getResultList().size();
        return count > 0 ? true : false;
    }
}