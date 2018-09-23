package com.codeboyq.daybook.presentation;

import com.codeboyq.daybook.api.dto.ItemDto;
import com.codeboyq.daybook.MainApp;
import com.codeboyq.daybook.entity.Item;
import com.codeboyq.daybook.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/" + MainApp.REST_URL_BASE)
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/v1/items")
    public ResponseEntity<Item> getItems(@RequestParam(name = "id", required = true) int id) {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @GetMapping("/v1/itemsold") @ResponseBody
    public ItemDto getItemsold(@RequestParam(name = "id", required = false, defaultValue = "Stranger") String name) {
        return new ItemDto(1, new Date(), "Dit is de tekst van het item");
    }

}
