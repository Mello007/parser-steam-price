package ru.price.service;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonNode;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    public String jsonChangeTostring(String json, String typeOfJson) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode JsonNode = objectMapper.readTree(json);
        return String.valueOf(JsonNode.findValue(typeOfJson)).replace("\"", "");
    }
}
