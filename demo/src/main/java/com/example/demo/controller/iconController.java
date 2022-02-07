package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Formation;
import com.example.demo.model.Photo;
import com.example.demo.model.icon;
import com.example.demo.service.iconservice;
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
public class iconController {
    @Autowired
    iconservice iconservice;
    private static final String ENTITY_NAME = "icon";

    @PostMapping(value = "/icon", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })

    public icon post(@RequestPart("company_id") String company_id, @RequestPart("icon") MultipartFile icon) throws IOException, ResourceNotFoundException {

        icon userJson = iconservice.getJson(company_id,  icon);

        return userJson;
    }



    @PutMapping(value = "/icon/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<icon> updateiconById(@PathVariable Integer id, @RequestPart("icon") MultipartFile icon) throws MethodArgumentNotValidException, ResourceNotFoundException, IOException {

        icon userJson = iconservice.update(id,  icon);

        return ResponseEntity.ok().body(userJson);
    }
    /*****************new********/
    @GetMapping("/icon/{company_Id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String company_Id) {


        icon dbFile = iconservice.getFile(company_Id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getIconType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getIconName() + "\"")
                .body(new ByteArrayResource(dbFile.getIcon()));
    }
    @GetMapping("/icon")
    public List<icon> findAll() {
        return iconservice.findAll();
    }


    @GetMapping("/iconblob/{companyid}")
    public byte[] blob(@PathVariable String companyid) {
        return iconservice.blob(companyid);
    }

    @PutMapping(value = "/iconblob/{companyid}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<icon> updateiconByIdStudentid(@PathVariable String companyid, @RequestPart("icon") MultipartFile icon) throws MethodArgumentNotValidException, ResourceNotFoundException, IOException {

        icon userJson = iconservice.updateicon(companyid,  icon);

        return ResponseEntity.ok().body(userJson);
    }
}
