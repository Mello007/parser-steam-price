package ru.price.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.price.Item;
import ru.price.service.ItemService;
import ru.price.service.PriceService;

import java.util.List;

@RestController
@RequestMapping(value = "item")
public class MainController {

    @Autowired
    PriceService priceService;
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Item addItem(@RequestBody String itemInfo) throws Exception {
        return priceService.setPrice(itemInfo);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Item> getItems() throws Exception{
        return itemService.getAll();
    }

    @RequestMapping(value = "curr", method = RequestMethod.POST)
    public void setCurr(@RequestBody String currItem) throws Exception{
        itemService.setCurr(currItem);
    }
}
