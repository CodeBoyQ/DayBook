package com.codeboyq.daybook.presentation;

import com.codeboyq.daybook.DayBookException;
import com.codeboyq.daybook.MainApp;
import com.codeboyq.daybook.api.dto.FileUploadResponseDto;
import com.codeboyq.daybook.api.dto.ItemDto;
import com.codeboyq.daybook.entity.Item;
import com.codeboyq.daybook.service.IItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/" + MainApp.REST_URL_BASE)

public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/v1/items/{id}")
    public ResponseEntity<?> getItem(@PathVariable(name = "id", required = true) Integer id) {
        List<ItemDto> items = new ArrayList<>();
        ItemDto itemDto;
        try {
            itemDto = convertToDto(itemService.getItemById(id));
        } catch (DayBookException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        items.add(itemDto);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/v1/items")
    public ResponseEntity<?> getItems() {
        List<ItemDto> items = new ArrayList<>();
        itemService.getAllItems().forEach(itemEntity -> items.add(convertToDto(itemEntity)));
        return ResponseEntity.ok(items);
    }

    @PostMapping("/v1/items")
    public ResponseEntity<?> addItem(@RequestBody ItemDto item) {

        Item newItem;

        try {
            newItem = itemService.addItem(convertToEntity(item));
        } catch (DayBookException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }

        return ResponseEntity.ok(convertToDto(newItem));

    }

    @PutMapping("/v1/items")
    public ResponseEntity<?> updateitem(@RequestBody ItemDto item) {

        Item updatedItem;

        try {
            updatedItem = itemService.updateItem(convertToEntity(item));
        } catch (DayBookException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }

        return ResponseEntity.ok(convertToDto(updatedItem));

    }

    @DeleteMapping("/v1/items/{id}")
    public ResponseEntity<Void> deletItem(@PathVariable("id") int id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/items/{id}/image")
    public ResponseEntity<?> getImage (@PathVariable(name = "id", required = true) int itemId, HttpServletRequest request) {
        Resource resource = null;
        try {
            resource = itemService.getImage(itemId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            //logger.info("Could not determine file type."); TODO
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PutMapping("/v1/items/{id}/image")
    public ResponseEntity<?> setImage(@PathVariable(name = "id", required = true) int itemId, @RequestParam("image") MultipartFile imageFile) throws Exception {

        String dosExtension;
        if (imageFile.getContentType().equals("image/jpeg")) {
            dosExtension = "jpg";
        } else if (imageFile.getContentType().equals("image/png")) {
            dosExtension = "png";
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The file is not a jpg or png. Please provide a jpg or png file");
        }

        Path destinationPath = itemService.setImage(itemId, imageFile.getInputStream(), dosExtension);

        FileUploadResponseDto frDto = new FileUploadResponseDto(
                itemId,
                destinationPath.toString(),
                imageFile.getContentType(),
                imageFile.getSize());

        return ResponseEntity.ok(frDto);
    }

    @DeleteMapping("/v1/items/{id}/image")
    public ResponseEntity<Void> deleteImage(@PathVariable(name = "id", required = true) int itemId) throws Exception {

        itemService.deleteImage(itemId);

        return ResponseEntity.ok().build();
    }

    private ItemDto convertToDto(Item item) {
        ItemDto itemDto = modelMapper.map(item, ItemDto.class);
        return itemDto;
    }

    private Item convertToEntity(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        return item;
    }


}
