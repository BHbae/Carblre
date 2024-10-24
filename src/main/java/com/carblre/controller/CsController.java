package com.carblre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cs")
public class CsController {

    @GetMapping("/cs")
    public String csPage() {
        return "cs/cs";
    }

    @GetMapping("/write")
    public String csWritePage() {
        return "cs/write";
    }

}
