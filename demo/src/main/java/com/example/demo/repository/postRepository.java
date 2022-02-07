package com.example.demo.repository;
import com.example.demo.model.Post;
import com.example.demo.model.Poststate;
import com.example.demo.model.icon;
import com.example.demo.payload.top3Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

public interface postRepository extends JpaRepository<Post, Long> {


    List<Post> findByCompanyid(String company_id);
    List<Post> findByPoststateOrderByPostedateDesc( Poststate poststate);

    List<Post> findByPoststateAndTypeOrderByPostedateDesc( Poststate poststate, String type);
    List<Post> findByPoststateAndCityOrderByPostedateDesc( Poststate poststate, String city);
    List<Post> findByPoststateAndTypeAndCityOrderByPostedateDesc( Poststate poststate,String type ,String city);
    List<Post> findByIdIn(List<Long> IdsList);
    @Query(value = "SELECT * FROM post WHERE postedate Like %?1%", nativeQuery = true)
    List<Post> findByMatchMonthAndYear(@Param("eventDate") String eventDate);

    @Query(value = "SELECT * FROM post WHERE postedate Like %?1% and companyid = ?2", nativeQuery = true)
    List<Post> findByMatchMonthAndYearAndCompanyId(@Param("eventDate") String eventDate, @Param("comanyid") String comanyid);

    @Query(value = "select companyname , count(id) as total from post group by companyname order by total desc limit 3", nativeQuery = true)
    List<faza>getto3();
}
