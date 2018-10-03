package com.codeboyq.daybook.service;

import com.codeboyq.daybook.DayBookException;
import com.codeboyq.daybook.entity.Item;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@Service
public class PictureService implements IPictureService {

    public static String  REPOSITORY = "src/main/resources/repo";

    public Path storeImage(Item item, InputStream is, String dosExtension) throws DayBookException {
        LocalDate date = item.getDate();
        if (date == null) {
            throw new DayBookException("Item has an empty date!");
        }


        //Construct the folder path
        Path folderPath = Paths.get(
                REPOSITORY ,
                String.valueOf(date.getYear()),
                String.format("%02d", date.getMonthValue())
        );

        //Create it if it doesn't exist
        if (!Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                throw new DayBookException(e);
            }
        }

        //Construct the file path
        Path filePath = Paths.get(
                folderPath.toString(),
                String.format("%02d", date.getDayOfMonth()).concat("." + dosExtension)
        );

        //Create the file
        try {
            Files.copy(
                    is,
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new DayBookException(e);
        }

        return filePath;
    }

    public Resource retrieveImage(Item item) throws DayBookException {
        try {
            Path filePath = Paths.get(item.getImagePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new DayBookException("File not found");
            }
        } catch (MalformedURLException ex) {
            throw new DayBookException("File not found", ex);
        }
    }
}
