package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;

import java.io.InputStream;
import java.nio.file.Path;

public interface IPictureService {

    Path storeImage(Item item, InputStream is) throws Exception;
}
