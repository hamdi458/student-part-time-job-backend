package com.example.demo.repository;

import com.example.demo.model.Experience;
import com.example.demo.model.skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface skillsRepository  extends JpaRepository<skills, Long> {
    List<skills> findByStudintcin(String studentid);
}
