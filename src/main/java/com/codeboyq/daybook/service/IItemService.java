package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAllItems();

    Item getItemById(int articleId);

    boolean addItem(Item article);

    void updateItem(Item article);

    void deleteItem(int articleId);
}
