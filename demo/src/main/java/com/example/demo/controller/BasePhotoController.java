/*
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BasePhoto;
import com.example.demo.model.Photo;
import com.example.demo.service.BasePhotoservice;
import com.example.demo.service.Photoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class BasePhotoController {

    @Autowired
    com.example.demo.service.BasePhotoservice BasePhotoservice;
    private static final String ENTITY_NAME = "BasePhoto";

    @PostMapping(value = "/basephoto")

    public BasePhoto post(@RequestPart("id") Long id, @RequestPart("basephoto") MultipartFile basephoto) throws IOException, ResourceNotFoundException {

        BasePhoto userJson = BasePhotoservice.getJson(id,  basephoto);

        return userJson;
    }

}
*/