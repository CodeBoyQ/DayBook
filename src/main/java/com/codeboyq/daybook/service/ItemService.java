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
    public Item getItemById(int articleId) {
        Item obj = itemDao.getItemById(articleId);
        return obj;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public synchronized boolean addItem(Item article) {
        if (itemDao.itemExists("todo1", "todo2")) {
            return false;
        } else {
            itemDao.addItem(article);
            return true;
        }
    }

    @Override
    public void updateItem(Item article) {
        itemDao.updateItem(article);
    }

    @Override
    public void deleteItem(int articleId) {
        itemDao.deleteItem(articleId);
    }
}