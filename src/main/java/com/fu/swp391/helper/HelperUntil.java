package com.fu.swp391.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class HelperUntil {

    public ObjectNode convertToJson(String key,String value){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseBody = mapper.createObjectNode();
        responseBody.put(key,value);
        return responseBody;
    }
    public void putKeyValue(ObjectNode node,String key,String value){
       node.put(key,value);
    }

    public void putKeyValue(ObjectNode node,String key,int value){
        node.put(key,value);
    }
}
