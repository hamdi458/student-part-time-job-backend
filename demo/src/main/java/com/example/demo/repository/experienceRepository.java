package com.example.demo.repository;

import com.example.demo.model.Experience;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface experienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByStudintcinnn(String studentid);
}
