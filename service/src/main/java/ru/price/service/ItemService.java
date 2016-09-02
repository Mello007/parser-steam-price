package ru.price.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.price.Item;

import java.util.List;

@Service
public class ItemService {

    @Autowired SessionFactory sessionFactory;

    @Transactional
    public List<Item> getAll() throws Exception{
        Query query = sessionFactory.openSession().createQuery("from Item");
        List<Item> items = query.list();
        for (Item item : items){
            item.getId();
            item.getItemName();
            item.getItemPrice();
        }
        return items;
    }
    

    @Transactional
    public void setCurr(String currItem) throws Exception {
        String numberCurr;
        JsonService jsonService = new JsonService();
        String ChangeCurrItem = jsonService.jsonChangeTostring(currItem, "itemCurr");
        switch (ChangeCurrItem){
            case "rub":
                numberCurr = "5";
                break;
            case "eur":
                numberCurr = "3";
                break;
            case "usd":
                numberCurr = "1";
                break;
            default: numberCurr = "5";
        }
        List<Item> items = getAll();
        for (Item item : items){
            item.setItemCurr(numberCurr);
            sessionFactory.getCurrentSession().merge(item);
        }
    }
}
