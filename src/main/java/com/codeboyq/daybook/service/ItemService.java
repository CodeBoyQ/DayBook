package com.codeboyq.daybook.service;

import com.codeboyq.daybook.api.dao.IItemDao;
import com.codeboyq.daybook.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Item getItemById(int itemId) {
        Item obj = itemDao.getItemById(itemId);
        return obj;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public synchronized Item addItem(Item item) {
        if (itemDao.itemExists(item.getDate())) {
            return null;
        } else {
            return itemDao.addItem(item);
        }
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public Path setImage(int itemId, InputStream is, String dosExtension) throws Exception {
        Item item = getItemById(itemId);
        Path storedPath = pictureService.storeImage(item, is, dosExtension);
        item.setImagePath(storedPath.toString());
        updateItem(item);
        return storedPath;
    }

    @Override
    public void deleteItem(int itemId) {
        itemDao.deleteItem(itemId);
    }
}