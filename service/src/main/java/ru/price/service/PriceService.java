package ru.price.service;


import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonNode;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.price.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class PriceService {

    @Autowired SessionFactory sessionFactory;

    @Transactional
    public Item setPrice(String jsonItem) throws Exception {
        JsonService jsonService = new JsonService();
        String itemName = jsonService.jsonChangeTostring(jsonItem, "itemName");
        String itemKind = jsonService.jsonChangeTostring(jsonItem, "itemKind");
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemKind(itemKind);
        item.setItemPrice(setRequest(item));
        sessionFactory.getCurrentSession().save(item);
        return item;
    }

    @Transactional
    public String makeLinkForRequest(Item item){
        String reqUrl;
        if (item.getItemKind() == null){
                 reqUrl = "http://steamcommunity.com/market/priceoverview/?appid=730&currency={ITEM_CURR}&market_hash_name={ITEM_NAME}"
                .replace("{ITEM_NAME}", item.getItemName().replace(" ", "%20"))
                .replace("{ITEM_CURR}", item.getItemCurr());
        } else {
            reqUrl = "http://steamcommunity.com/market/priceoverview/?appid=730&currency={ITEM_CURR}&market_hash_name={ITEM_NAME}%20%28{ITEM_KIND}%29"
                    .replace("{ITEM_NAME}", item.getItemName().replace(" ", "%20").replace("|", "%7C"))
                    .replace("{ITEM_CURR}", item.getItemCurr())
                    .replace("{ITEM_KIND}", item.getItemKind().replace(" ", "%20"));
        }
        return reqUrl;
    }

    @Transactional
    public String setRequest(Item item) throws Exception{
        URL obj = new URL(makeLinkForRequest(item));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.toString());
        return String.valueOf(actualObj.findValue("lowest_price")).replace("\"", "");
    }
}
