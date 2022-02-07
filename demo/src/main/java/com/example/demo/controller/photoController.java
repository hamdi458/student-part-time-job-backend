package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Photo;

import com.example.demo.service.Photoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class photoController {
    @Autowired
    Photoservice photoservice;
    private static final String ENTITY_NAME = "Photo";

    @PostMapping(value = "/photo", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })

    public Photo post(@RequestPart("student_id") String student_id, @RequestPart("photo") MultipartFile photo) throws IOException, ResourceNotFoundException {

        Photo userJson = photoservice.getJson(student_id,  photo);

        return userJson;
    }



    @PutMapping(value = "/photo/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Photo> updateiconById(@PathVariable Integer id, @RequestPart("photo") MultipartFile photo) throws MethodArgumentNotValidException, ResourceNotFoundException, IOException {

        Photo userJson = photoservice.update(id,  photo);

        return ResponseEntity.ok().body(userJson);
    }
    /*****************new********/
    @GetMapping("/photo/{student_Id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String student_Id) {


        Photo dbFile = photoservice.getFile(student_Id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getPhotoType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getPhotoName() + "\"")
                .body(new ByteArrayResource(dbFile.getPhoto()));
    }
    @GetMapping("/photo")
    public List<Photo> findAll() {
        return photoservice.findAll();
    }


    @GetMapping("/photoblob/{studentid}")
    public byte[] blob(@PathVariable String studentid) {
        return photoservice.blob(studentid);
    }

    @PutMapping(value = "/photoblob/{studentid}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Photo> updateiconByIdStudentid(@PathVariable String studentid, @RequestPart("photo") MultipartFile photo) throws MethodArgumentNotValidException, ResourceNotFoundException, IOException {

        Photo userJson = photoservice.updatephoto(studentid,  photo);

        return ResponseEntity.ok().body(userJson);
    }
}
