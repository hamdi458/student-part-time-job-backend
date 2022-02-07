package com.example.demo.repository;

import com.example.demo.model.Post;
import com.example.demo.model.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface reactRepository extends JpaRepository<React, Long> {

    List<React> findByPostid(Long postid);
    List<React> findByStudintcinnnn(String postid);
    Long deleteByPostidAndStudintcinnnn(Long postid,String studentid);
    React getByPostidAndStudintcinnnn(Long postid,String studentid);

    @Query(value = "SELECT * FROM react WHERE ractdate Like %?1% and reactstate=?2", nativeQuery = true)
    List<React> findByMatchMonthAndYear(@Param("eventDate") String eventDate, @Param("type") String type);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM react WHERE id in ?1", nativeQuery = true)
    Integer deleteByIdIn(List<Integer> ids);

}
