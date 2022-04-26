package com.fu.swp391.binding.entiity;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Data
@Component
public class UserCandidate {
    @Valid
    public User user;
    @Valid
    public Candidate candidate;


    public List<String> genders;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
//
//    public setDobByString(String date){
//
//    }
}
