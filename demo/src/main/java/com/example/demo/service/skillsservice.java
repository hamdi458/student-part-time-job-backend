package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.skills;
import com.example.demo.repository.skillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class skillsservice {
    @Autowired
    private skillsRepository skillsrepository;
/* ---------------------------------get all------------------*/
    public List<skills> findAll() {
        return skillsrepository.findAll();


    }
    /*--------------------------save one ------------------*/
    public skills save(skills sk) {
        return skillsrepository.save(sk);
    }

/* ------------------------update skills----------------- */
    public skills update(skills sk) throws ResourceNotFoundException {

        skills inBase = skillsrepository.findById(sk.getId())
                .orElseThrow(() -> new ResourceNotFoundException("skills not found for this id :: " + sk.getId()));
        inBase.setName(sk.getName());
        inBase.setNote(sk.getNote());

        inBase = skillsrepository.save(inBase);
        return inBase;
    }
    /* ---------------find one---------*/
    public skills findOne(long id) throws ResourceNotFoundException {

        skills inBase = skillsrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("skills not found for this id :: " + id));


        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {

        skillsrepository.deleteById(id);
    }

    /* ---------------------------------SKILLSByStudentId------------------*/
    public List<skills> skillsByStudentId(String studentid) {
        return skillsrepository.findByStudintcin(studentid);


    }


}
