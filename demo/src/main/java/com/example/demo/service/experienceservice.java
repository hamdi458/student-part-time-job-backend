package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Experience;
import com.example.demo.model.skills;
import com.example.demo.repository.experienceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class experienceservice {
    @Autowired
    private experienceRepository experienceRepositoryepository;

    /* ---------------------------------get all------------------*/
    public List<Experience> findAll() {
        return experienceRepositoryepository.findAll();


    }
    /*--------------------------save one ------------------*/
    public Experience save(Experience exp) {
        return experienceRepositoryepository.save(exp);
    }

    /* ------------------------update skills----------------- */
    public Experience update(Experience sk) throws ResourceNotFoundException {

        Experience inBase = experienceRepositoryepository.findById(sk.getId())
                .orElseThrow(() -> new ResourceNotFoundException("EXPERIENCE not found for this id :: " + sk.getId()));
        inBase.setName(sk.getName());
        inBase.setDateend(sk.getDateend());
        inBase.setDatestart(sk.getDatestart());
        inBase.setPost(sk.getPost());
        inBase.setDescription(sk.getDescription());
        inBase = experienceRepositoryepository.save(inBase);
        return inBase;
    }
    /* ---------------find one---------*/
    public Experience findOne(long id) throws ResourceNotFoundException {

        Experience inBase = experienceRepositoryepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EXPERIENCE not found for this id :: " + id));

        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {

        experienceRepositoryepository.deleteById(id);
    }



    /* ---------------------------------experienceByStudentId------------------*/
    public List<Experience> experienceByStudentId(String studentid) {
        return experienceRepositoryepository.findByStudintcinnn(studentid);


    }
}
