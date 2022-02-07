package com.example.demo.repository;

import com.example.demo.model.Compte;
import com.example.demo.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface compteRepository extends JpaRepository<Compte, String> {
  Compte  findByEmailAndPassword(String id, String password);
  Compte findByEmailIgnoreCase(String emailId);
}
