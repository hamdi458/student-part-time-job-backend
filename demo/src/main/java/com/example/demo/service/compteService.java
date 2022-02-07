package com.example.demo.service;

import com.example.demo.exception.ErrorDetails;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.compteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

@Service
@Transactional
public class compteService {
    @Autowired
    private compteRepository compteRepository;
    @Autowired
    private studentservice studentservice;
    @Autowired
    companyservice  companyservice;
    @Autowired
    private DBFileStorageService DBFileStorageService;
    @Autowired
    private Photoservice Photoservice ;
    @Autowired
    private iconservice iconservice;
    /*--------------------------save one ------------------*/
    public Compte save(Compte exp) {
        boolean a = isValid(exp.getEmail());
                if (a == false) {exp.setEmail(null);    }
        byte[] image = DBFileStorageService.getblob("117e7f98-9a40-4b6d-af8e-57b4a5d862c6");
                DBFile az  =DBFileStorageService.getFile("117e7f98-9a40-4b6d-af8e-57b4a5d862c6");


                Compte c =compteRepository.save(exp);
                if (c.getType()==0) {
                    Student st = new Student(c.getEmail(), c.getName(), c.getLastname());
                    studentservice.save(st);
                    Photo ph = new Photo(image, az.getFileName(),az.getFileType(),c.getEmail());
                    Photoservice.save(ph);

                }
                else {
                    Company com = new Company(c.getEmail(), c.getName());
                    companyservice.save(com);
                    icon ic = new icon(image,c.getEmail());
                    iconservice.save(ic);

                }
                return c;


    }
    /* ---------------find one---------*/
    public Compte findOne(String id) throws ResourceNotFoundException {

        Compte inBase = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found for this id :: " + id));

        return inBase;
    }

    public Compte findbEmailAndPassword(String id, String password) throws ResourceNotFoundException {

        Compte inBase = compteRepository.findByEmailAndPassword(id, password);


        return inBase;
    }

    public void delete(String id) {

        compteRepository.deleteById(id);
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
