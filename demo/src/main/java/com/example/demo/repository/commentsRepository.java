package com.example.demo.repository;

import com.example.demo.model.Comments;
import com.example.demo.model.React;
import com.example.demo.model.skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByStuudentid(String studentid);
}
