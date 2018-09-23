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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/" + MainApp.REST_URL_BASE)

public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/v1/uploadFile")
    public ResponseEntity<FileUploadResponseDto> uploadFile(@RequestParam(name = "tokinput", required = false, defaultValue = "wakadaka") String tokyo, @RequestParam("file") MultipartFile mpFile) {


        String fileName = mpFile.getOriginalFilename() + " " + tokyo;


//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();



        FileUploadResponseDto frDto = new FileUploadResponseDto(fileName, "Dit is waar het bestand terecht is gekomen" + tokyo,
                mpFile.getContentType(), mpFile.getSize());
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
    public ResponseEntity<List<ItemDto>> createItem(@RequestBody ItemDto item) {
        List<ItemDto> items = new ArrayList<>();
        itemService.getAllItems().forEach(itemEntity -> items.add(convertToDto(itemEntity)));
        return new ResponseEntity<List<ItemDto>>(items, HttpStatus.OK);
    }

    @GetMapping("/v1/itemsold")
    @ResponseBody
    public ItemDto getItemsold(@RequestParam(name = "id", required = false, defaultValue = "Stranger") String name) {
        return new ItemDto(3, new Date(), "Dit is de tekst van het item", true);
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
