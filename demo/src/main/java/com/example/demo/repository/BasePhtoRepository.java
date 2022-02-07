package com.example.demo.repository;

import com.example.demo.model.BasePhoto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BasePhtoRepository extends JpaRepository<BasePhoto, Long> {
}
