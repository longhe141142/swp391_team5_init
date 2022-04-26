package com.fu.swp391.common.enumConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderEnum {


    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    private List<Gender> Genders = new ArrayList<Gender>();
    public GenderEnum(){
        Gender g1 = new Gender();
        g1.setName(MALE);
        Gender g2 = new Gender();
        g2.setName(FEMALE);
        this.getGenders().add(g1);
        this.getGenders().add(g2);
    }


    public List<Gender> getGenders() {
        return Genders;
    }

    public void setGenders(List<Gender> genders) {
        Genders = genders;
    }
}
