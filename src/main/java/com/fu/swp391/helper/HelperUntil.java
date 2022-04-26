package com.fu.swp391.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HelperUntil<E> {

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

    public ArrayList<E> PagingElement(ArrayList<E> elements,int pageNumber,int numberOfObjectInOnePage ){
        ArrayList<E> newList = new ArrayList<E>();

        int start =  numberOfObjectInOnePage * (pageNumber - 1);
        int end = start + numberOfObjectInOnePage - 1;
        if(end>elements.size()-1){
            end = elements.size() -1 ;
        }
        for(int i = start;i<=end;++i){
            newList.add(elements.get(i));
        }
        return newList;
    }

    public int getTotalSize(int sizeList,int perPage){
        if(sizeList==0)
            return 1;
        double checkParam = ((double)sizeList/perPage - (double)(sizeList/perPage));
        return  checkParam==0? sizeList/perPage : sizeList/perPage+1;
    }


    public String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
