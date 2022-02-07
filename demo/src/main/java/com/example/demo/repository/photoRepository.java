package com.example.demo.repository;

import com.example.demo.model.Photo;
import com.example.demo.model.icon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface photoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByStudentid(String student_id);
}
