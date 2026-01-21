package com.ra12.projecte1.com_ra12_projecte1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.com_ra12_projecte1.service.ObjectService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/trokka")
public class ObjectController {
    @Autowired
    ObjectService objectService;

    @PostMapping("objecte/{id}/image")
    public ResponseEntity<String> postImageObject(@RequestParam MultipartFile entity) {
        
    }
    
}
