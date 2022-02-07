package com.example.demo.repository;



import com.example.demo.model.icon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iconRepository extends JpaRepository<icon, Long> {
    Optional<icon> findByCompanyid(String company_id);
}
