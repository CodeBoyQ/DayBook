package com.codeboyq.daybook.presentation;

import com.codeboyq.daybook.MainApp;
import com.codeboyq.daybook.api.dto.FileUploadResponseDto;
import com.codeboyq.daybook.api.dto.ItemDto;
import com.codeboyq.daybook.entity.Item;
import com.codeboyq.daybook.service.IItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping("/v1/items/{id}/setimage")
    public ResponseEntity<?> uploadFile(@PathVariable(name = "id", required = true) int itemId, @RequestParam("image") MultipartFile imageFile) throws Exception {

        String dosExtension;
        if (imageFile.getContentType().equals("image/jpeg")) {
            dosExtension = "jpg";
        } else if (imageFile.getContentType().equals("image/png")) {
            dosExtension = "png";
        } else {
            return new ResponseEntity<String>("The file is not a jpg or png. Please provide a jpg or png file", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Path destinationPath = itemService.setImage(itemId, imageFile.getInputStream(), dosExtension);

        FileUploadResponseDto frDto = new FileUploadResponseDto(
                itemId,
                destinationPath.toString(),
                imageFile.getContentType(),
                imageFile.getSize());

        return new ResponseEntity<FileUploadResponseDto>(frDto, HttpStatus.OK);
    }

    @GetMapping("/v1/items")
    public ResponseEntity<List<ItemDto>> getItem(@RequestParam(name = "id", required = false) Integer id) {
        List<ItemDto> items = new ArrayList<>();
        if (id==null) {
            itemService.getAllItems().forEach(itemEntity -> items.add(convertToDto(itemEntity)));
        } else {
            ItemDto itemDto = convertToDto(itemService.getItemById(id));
            items.add(itemDto);
        }
        return new ResponseEntity<List<ItemDto>>(items, HttpStatus.OK);
    }

    @PostMapping("/v1/items")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto item) {

        Item newItem = itemService.addItem(convertToEntity(item));

        if (newItem!=null) {
            return new ResponseEntity<ItemDto>(convertToDto(newItem), HttpStatus.OK);
        }

        return new ResponseEntity<ItemDto>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PutMapping("/v1/items")
    public ResponseEntity<Void> updateitem(@RequestBody ItemDto item) {
        itemService.updateItem(convertToEntity(item));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/v1/items/{id}")
    public ResponseEntity<Void> deletItem(@PathVariable("id") int id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
