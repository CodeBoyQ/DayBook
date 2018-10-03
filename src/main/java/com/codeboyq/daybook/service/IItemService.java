package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface IItemService {
    List<Item> getAllItems();

    Item getItemById(int itemId);

    Item addItem(Item item);

    void updateItem(Item item);

    void deleteItem(int itemId);

    Path setImage (int itemId, InputStream is, String dosExtension) throws Exception;
}
