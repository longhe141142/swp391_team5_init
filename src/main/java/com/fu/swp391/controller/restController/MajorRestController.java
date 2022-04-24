package com.fu.swp391.controller.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.controller.restController.dto.SkillDTO;
import com.fu.swp391.controller.restController.dto.ruleDTO;
import com.fu.swp391.entities.Skill;
import com.fu.swp391.service.MajorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("company/major")
public class MajorRestController {
    @Autowired
    MajorService majorService ;
    @RequestMapping(value = "/rule",  method = RequestMethod.POST)
    @ResponseBody
    public String getRule(@RequestBody List<String> string)
    {

        ObjectMapper mapper = new ObjectMapper();
        String ajaxResponse = "";
        try {
            ajaxResponse = mapper.writeValueAsString("");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ajaxResponse;
    }

}
