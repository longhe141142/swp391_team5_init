package com.fu.swp391.binding.entiity;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.User;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Data
public class UserCandidate {
    @Valid
    public User user;
    @Valid
    public Candidate candidate;

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
}
