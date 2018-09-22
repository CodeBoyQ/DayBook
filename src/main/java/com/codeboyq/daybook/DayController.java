package com.codeboyq.daybook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class DayController {

    @GetMapping("/" + MainApp.REST_URL_BASE + "/v1/items")
    @ResponseBody
    public ItemDto sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new ItemDto(1,"wola");
    }

}
