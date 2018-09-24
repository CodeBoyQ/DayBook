package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAllItems();

    Item getItemById(int itemId);

    boolean addItem(Item item);

    void updateItem(Item item);

    void deleteItem(int itemId);
}
