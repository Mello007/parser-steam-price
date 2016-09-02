package ru.price.service;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.price.Item;

import java.util.List;

@Service
@Component
public class UpdateService {

    @Autowired SessionFactory sessionFactory;

    @Transactional
    @Scheduled(fixedRate = 50000)
    public void getNewItemPrices() throws Exception{
        PriceService priceService = new PriceService();
        Query query = sessionFactory.openSession().createQuery("from Item");
        List<Item> items = query.list();
        for (Item item : items){
            item.setItemPrice(priceService.setRequest(item));
            sessionFactory.getCurrentSession().merge(item);
        }
    }
}
