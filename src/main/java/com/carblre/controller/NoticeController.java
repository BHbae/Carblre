package com.carblre.controller;

import com.carblre.repository.model.AccidentDamageCount;
import com.carblre.repository.model.DeathToYearCount;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping("/notice")
    public String noticePage() {
        return "notice/notice";
    }

}
