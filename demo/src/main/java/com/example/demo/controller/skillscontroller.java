package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;


import com.example.demo.model.skills;
import com.example.demo.service.skillsservice;
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
public class skillscontroller {
    @Autowired
    skillsservice skillsservice;
    private static final String ENTITY_NAME = "skills";


// get all skills
    @GetMapping("/skills")
    public List<skills> findAll() {
        return skillsservice.findAll();
    }
// create new one
    @PostMapping("/skills")
    public skills postskills(@RequestBody skills skills){

        skills result = skillsservice.save(skills);
        return result;
    }
// delete by id
    @DeleteMapping("/skills/{id}")
    public Map<String, Boolean> deleteskills(@PathVariable(value = "id") Long skillId)
            throws ResourceNotFoundException {
        skillsservice.delete(skillId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
// get by id
    @GetMapping("/skills/{id}")
    public ResponseEntity<skills> getskillbyid(@PathVariable Integer id) throws ResourceNotFoundException {

        skills dto = skillsservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
// put skills
    @PutMapping("/skills/{id}")
    public ResponseEntity<skills> updateSkillById(@PathVariable Integer id,  @RequestBody skills skills) throws MethodArgumentNotValidException, ResourceNotFoundException {

        skills.setId(id);
        skills result =skillsservice.update(skills);
        return ResponseEntity.ok().body(result);
    }

// skillsByStudentId
    @GetMapping("/skillsByStudentId/{studentid}")
    public List<skills> skillsByStudentId(@PathVariable String studentid) throws ResourceNotFoundException {

        List<skills> dto = skillsservice.skillsByStudentId(studentid);

        return dto;
    }
}
