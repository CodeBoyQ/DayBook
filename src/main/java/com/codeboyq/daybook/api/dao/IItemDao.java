package com.codeboyq.daybook.api.dao;

import com.codeboyq.daybook.entity.Item;

import java.sql.Date;
import java.util.List;

public interface IItemDao {

        List<Item> getAllItems();
        Item getItemById(int itemId);
        void addItem(Item item);
        void updateItem(Item item);
        void deleteItem(int articleId);
        boolean itemExists(Date date);

}
