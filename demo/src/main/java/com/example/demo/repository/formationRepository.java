package com.example.demo.repository;


import com.example.demo.model.Experience;
import com.example.demo.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface formationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByStudintcinn(String studentid);
}
