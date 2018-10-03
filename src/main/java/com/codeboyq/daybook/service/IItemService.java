package com.codeboyq.daybook.service;

import com.codeboyq.daybook.DayBookException;
import com.codeboyq.daybook.entity.Item;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface IItemService {
    List<Item> getAllItems();

    Item getItemById(int itemId) throws DayBookException;

    Item addItem(Item item) throws DayBookException;

    Item updateItem(Item item) throws DayBookException;

    void deleteItem(int itemId);

    Path setImage (int itemId, InputStream is, String dosExtension) throws DayBookException;

    Resource getImage (int itemId) throws Exception;
}
