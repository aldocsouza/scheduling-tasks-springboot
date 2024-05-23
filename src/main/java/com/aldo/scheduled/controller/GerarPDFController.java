package com.aldo.scheduled.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
public class GerarPDFController {
    @GetMapping("/generate-pdf")
    public String gerarPdf(){
        return "gerarPdf";
    }
}
