package com.jackbourner.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<String>("GET Response 2", HttpStatus.OK);
    }
}
