package com.example.demo.repository;


import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface studentRepository extends JpaRepository<Student, String>{
    List<Student> findByCinIn(List<String> IdsList);

}
