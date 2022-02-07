package com.example.demo.service;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.iconRepository;
import com.example.demo.repository.studentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class iconservice {

    @Autowired
    private iconRepository iconrepository;
    @Autowired
    private companyservice companyservice;

    /*-----------new icon ------------------------*/
    public icon getJson(String company_id,  MultipartFile ph) throws IOException, ResourceNotFoundException {
/****new*/

        String fileName = StringUtils.cleanPath(ph.getOriginalFilename());
        /******/
        icon userJson = new icon();
        ObjectMapper objectMapper = new ObjectMapper();
        userJson.setCompanyid(company_id);
        userJson.setIcon(ph.getBytes());
        /**new**/
        userJson.setIconName(fileName);
        userJson.setIconType(ph.getContentType());

        iconrepository.save(userJson);

        Company comp = companyservice.findOne(company_id);

        comp.setCompanyicon(userJson);

        return userJson;

    }
    /*-----------update icon ------------------------*/
    public icon update(long id, MultipartFile ph) throws ResourceNotFoundException, IOException {
        String fileName = StringUtils.cleanPath(ph.getOriginalFilename());

        icon inBase = iconrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("icon not found for this id :: " + id));

        inBase.setIcon(ph.getBytes());
        /**new**/
        inBase.setIconName(fileName);
        inBase.setIconType(ph.getContentType());

        inBase = iconrepository.save(inBase);
        return inBase;
    }
/*--------------get icon by company id ---------------*/
    public icon getFile(String company_id) {
        return  iconrepository.findByCompanyid(company_id)
                .orElseThrow(() -> new FileNotFoundException("icon not found with company id " + company_id));
    }
    public List<icon> findAll() {
        return iconrepository.findAll();


    }

    public byte[] blob(String company_id) {
        icon a = iconrepository.findByCompanyid(company_id)
                .orElseThrow(() -> new FileNotFoundException("Photo not found with student id " + company_id));

        return a.getIcon();
    }

    /*-----------update icon ------------------------*/

    public icon updateicon(String company_id, MultipartFile ph) throws ResourceNotFoundException, IOException {
        String fileName = StringUtils.cleanPath(ph.getOriginalFilename());

        icon inBase = iconrepository.findByCompanyid(company_id)
                .orElseThrow(() -> new ResourceNotFoundException("PHOTO not found for this student id :: " + company_id));

        inBase.setIcon(ph.getBytes());
        /**new**/
        inBase.setIconName(fileName);
        inBase.setIconType(ph.getContentType());

        inBase = iconrepository.save(inBase);
        return inBase;
    }
    /*--------------------------save one ------------------*/
    public icon save( icon sk) {
        return iconrepository.save( sk);
    }


}
