package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Formation;
import com.example.demo.model.skills;
import com.example.demo.repository.formationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class formationservice {

    @Autowired
    private formationRepository formationRepository;

    /* ---------------------------------get all------------------*/
    public List<Formation> findAll() {
        return formationRepository.findAll();


    }
    /*--------------------------save one ------------------*/
    public Formation save(Formation formation) {
        return formationRepository.save(formation);
    }

    /* ------------------------update formationills----------------- */
    public Formation update(Formation formation) throws ResourceNotFoundException {

        Formation inBase = formationRepository.findById(formation.getId())
                .orElseThrow(() -> new ResourceNotFoundException("formation not found for this id :: " + formation.getId()));
        inBase.setName(formation.getName());
        inBase.setDateend(formation.getDateend());
        inBase.setDatestart(formation.getDatestart());
        inBase.setStudintcin(formation.getStudintcin());
        inBase.setDescription(formation.getDescription());
        inBase.setPoste(formation.getPoste());
        inBase = formationRepository.save(inBase);
        return inBase;
    }
    /* ---------------find one---------*/
    public Formation findOne(long id) throws ResourceNotFoundException {

        Formation inBase = formationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found for this id :: " + id));

        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {

        formationRepository.deleteById(id);
    }


    /* ---------------------------------formationByStudentId------------------*/
    public List<Formation> formationsByStudentId(String studentid) {
        return formationRepository.findByStudintcinn(studentid);


    }
}
