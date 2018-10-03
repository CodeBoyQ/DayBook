package com.codeboyq.daybook.service;

import com.codeboyq.daybook.DayBookException;
import com.codeboyq.daybook.api.dao.IItemDao;
import com.codeboyq.daybook.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private IItemDao itemDao;

    @Autowired
    private PictureService pictureService;

    @Override
    public Item getItemById(int itemId) throws DayBookException {
        Item obj = itemDao.getItemById(itemId);

        if (obj == null) {
            throw new DayBookException("Item with id " + itemId + " does not exist!");
        }

        return obj;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public synchronized Item addItem(Item item) throws DayBookException {
        if (itemDao.itemExists(item.getDate())) {
            throw new DayBookException("Duplicate item. An item with this date already exists!");
        } else {
            return itemDao.addItem(item);
        }
    }

    @Override
    public synchronized Item updateItem(Item item) throws DayBookException {
        if (itemDao.itemExists(item.getDate(), item.getId())) {
            throw new DayBookException("Cannot update date. An item with this date already exists!");
        } else {
            return itemDao.updateItem(item);
        }

    }

    @Override
    public void deleteItem(int itemId) {
        itemDao.deleteItem(itemId);
    }

    @Override
    public Path setImage(int itemId, InputStream is, String dosExtension) throws DayBookException {
        Item item = getItemById(itemId);
        Path storedPath = pictureService.storeImage(item, is, dosExtension);
        item.setImagePath(storedPath.toString());
        item.setImageStatus(1);
        updateItem(item);
        return storedPath;
    }

    @Override
    public Resource getImage (int itemId) throws DayBookException {
        Item item = getItemById(itemId);
        return pictureService.retrieveImage(item);
    }

    @Override
    public void deleteImage (int itemId) throws DayBookException {
        Item item = getItemById(itemId);
        item.setImagePath("");
        item.setImageStatus(0);
        updateItem(item);
    }

}