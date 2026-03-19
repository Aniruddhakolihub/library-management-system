package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
    public String home() {   //redirect / to home.html inside static folder.

        return "redirect:/home.html";

    }

}