package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Formation;


import com.example.demo.model.skills;
import com.example.demo.service.formationservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class formationController {
    @Autowired
    formationservice formationservice;
    private static final String ENTITY_NAME = "Formation";

// get all
    @GetMapping("/Formation")
    public List<Formation> findAll() {
        return formationservice.findAll();
    }

    @PostMapping("/Formation")
    public Formation postFormation(@RequestBody Formation formation){

        Formation result = formationservice.save(formation);
        return result;
    }

    @DeleteMapping("/Formation/{id}")
    public Map<String, Boolean> deleteFormation(@PathVariable(value = "id") Long formationId)
            throws ResourceNotFoundException {
        formationservice.delete(formationId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
// formation by id
    @GetMapping("/Formation/{id}")
    public ResponseEntity<Formation> getFormationbyid(@PathVariable Integer id) throws ResourceNotFoundException {

        Formation dto = formationservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
// put formation
    @PutMapping("/Formation/{id}")
    public ResponseEntity<Formation> updateFormationById(@PathVariable Integer id,  @RequestBody Formation formation) throws MethodArgumentNotValidException, ResourceNotFoundException {

        formation.setId(id);
        Formation result =formationservice.update(formation);
        return ResponseEntity.ok().body(result);
    }
// formationByStudentId

    @GetMapping("/formationByStudentId/{studentid}")
    public List<Formation> formationByStudentId(@PathVariable String studentid) throws ResourceNotFoundException {

        List<Formation> dto = formationservice.formationsByStudentId(studentid);

        return dto;
    }
}
