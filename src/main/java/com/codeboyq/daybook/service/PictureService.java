package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

@Service
public class PictureService implements IPictureService {

    public static String  REPOSITORY = "src/main/resources/repo";

    public Path storeImage(Item item, InputStream is, String dosExtension) throws Exception {
        LocalDate date = item.getDate();
        if (date == null) {
            throw new Exception();
        }


        //Construct the folder path
        Path folderPath = Paths.get(
                REPOSITORY ,
                String.valueOf(date.getYear()),
                String.format("%02d", date.getMonthValue())
        );

        //Create it if it doesn't exist
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        //Construct the file path
        Path filePath = Paths.get(
                folderPath.toString(),
                String.format("%02d", date.getDayOfMonth()).concat("." + dosExtension)
        );

        //Create the file
        Files.copy(
                is,
                filePath,
                StandardCopyOption.REPLACE_EXISTING);

        return filePath;
    }
}
