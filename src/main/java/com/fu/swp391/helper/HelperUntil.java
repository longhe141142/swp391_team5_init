package com.fu.swp391.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fu.swp391.common.enumConstants.PatternEnum;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class HelperUntil<E> {

    @Autowired
    private JavaMailSender mailSender;
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

//    public Set<E> PagingElementBySet(HashSet<E> elements,int pageNumber,int numberOfObjectInOnePage ){
//        HashSet<E> newList = new HashSet<>();
//
//        int start =  numberOfObjectInOnePage * (pageNumber - 1);
//        int end = start + numberOfObjectInOnePage - 1;
//        if(end>elements.size()-1){
//            end = elements.size() -1 ;
//        }
//        for(int i = start;i<=end;++i){
//            newList.add(elements.);
//        }
//        return newList;
//    }

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
            userName = ((UserDetails) principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public int generateRandomNumber(int min, int max) {

        return (int) (Math.random() * (max -min))+ min;
    }

    public ArrayList<E> SortListRandomly(List<E> entityList) {
        Collections.shuffle(entityList);
        return (ArrayList<E>) entityList;
    }

    public ArrayList<E> pickScopeToRandom(List<E> entityList, int size) throws Exception {
        ArrayList<E> newList = new ArrayList<>();
        if (size > entityList.size()) {
            throw new Exception("input size exceed ArrayList size");
        }
        for (int i = 0; i < size; i++) {
            newList.add(entityList.get(i));
        }
        return newList;
    }

    public Date parseStringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(PatternEnum.DEF_DATE_FORMAT_PARTERN);
        return formatter.parse(date);
    }

    public String generateRandomEmail() {
        return this.givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(6)
            + this.generateRandomNumber(1, 50) + "@gmail.com";
    }


    //code di cop https://www.baeldung.com/java-random-string
    public String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(int size) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = size;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        return generatedString;
    }

}
