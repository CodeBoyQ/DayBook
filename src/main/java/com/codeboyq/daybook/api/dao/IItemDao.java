package com.codeboyq.daybook.api.dao;

import com.codeboyq.daybook.entity.Item;

import java.time.LocalDate;
import java.util.List;

public interface IItemDao {

        List<Item> getAllItems();
        Item getItemById(int itemId);
        Item addItem(Item item);
        Item updateItem(Item item);
        void deleteItem(int itemId);
        boolean itemExists(LocalDate date);
        boolean itemExists(LocalDate date, int excludedId);

}
