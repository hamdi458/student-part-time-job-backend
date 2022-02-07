package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.service.studentservice;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class studentController {
    @Autowired
    studentservice studentservice;
    private static final String ENTITY_NAME = "Student";
// get all students
    @GetMapping("/student")
    public List<Student> findAll() {
        return  studentservice.findAll();
    }
// create new student
    @PostMapping("/student")
    public Student postStudent(@RequestBody Student Student){

        Student result =  studentservice.save(Student);
        return result;
    }
// delete student
    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") String StudentId)
            throws ResourceNotFoundException {
        studentservice.delete(StudentId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
// get one by id
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentbyid(@PathVariable String id) throws ResourceNotFoundException {

        Student dto =  studentservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
// put student
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable String id,  @RequestBody Student Student) throws MethodArgumentNotValidException, ResourceNotFoundException {

        Student.setCin(id);
        Student result = studentservice.update(Student);
        return ResponseEntity.ok().body(result);
    }

    // update Views student
    @GetMapping("/studentupdateViews/{id}")
    public boolean studentupdateViews(@PathVariable String id) throws ResourceNotFoundException {

        Student dto =  studentservice.studentupdateViews(id);


        return true;
    }

// studentMonyWithUs
    @GetMapping("/studentMonyWithUs/{id}")
    public double studentMonyWithUs(@PathVariable String id) throws ResourceNotFoundException {

        double dto =  studentservice.studentMonyWithUs(id);

        return dto;
    }

// numberOfReactsBystudent
    @GetMapping("/numberOfReactsBystudent/{studentid}")
    public Integer numberOfReactsBystudent(@PathVariable String studentid) throws ResourceNotFoundException {

        Integer dto =  studentservice.numberOfReactsBystudent(studentid);

        return dto;
    }

// percentageofsuccess
    @GetMapping("/percentageofsuccess/{studentid}")
    public Integer percentageofsuccess(@PathVariable String studentid) throws ResourceNotFoundException {

        Integer dto =  studentservice.percentageofsuccess(studentid);

        return dto;
    }
// StudentreactsbyPostId
    @GetMapping("/StudentreactsbyPostId/{postid}")
    public List<Student> StudentreactsbyPostId(@PathVariable Integer postid)
    {
        return studentservice.findListSTUDENTReactByPostId(postid);
    }

}
