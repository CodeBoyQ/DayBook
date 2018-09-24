package com.codeboyq.daybook.service;

import com.codeboyq.daybook.api.dao.IItemDao;
import com.codeboyq.daybook.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemDao itemDao;

    @Override
    public Item getItemById(int itemId) {
        Item obj = itemDao.getItemById(itemId);
        return obj;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public synchronized boolean addItem(Item item) {
        if (itemDao.itemExists(item.getDate())) {
            return false;
        } else {
            itemDao.addItem(item);
            return true;
        }
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public void deleteItem(int itemId) {
        itemDao.deleteItem(itemId);
    }
}