package com.codeboyq.daybook.service;

import com.codeboyq.daybook.entity.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PictureService.class})
public class PictureServicesTest {

    @Autowired
    private PictureService pictureService;

    @Before
    public void beforeTest() {
        // Do nothing
    }

    @Test
    public void testStoreImage() throws Exception {

        //Create test item
        Item item = new Item();
        item.setDate(LocalDate.parse("2018-08-23"));
        item.setText("Leuke dag joh!");
        item.setImageStatus(0);
        item.setPublished(false);

        //Use test image
        File testPlaatje = new File("src/test/resources/plaatje.jpg");

        //Store the test image to the repository
        Path destinationPath = pictureService.storeImage(item, new FileInputStream(testPlaatje));

        Assert.assertEquals(Paths.get(PictureService.REPOSITORY, "2018", "08", "23.jpg"), destinationPath);

    }


}
