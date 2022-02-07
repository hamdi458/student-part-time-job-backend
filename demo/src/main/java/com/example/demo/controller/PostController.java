package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;


import com.example.demo.model.Post;

import com.example.demo.model.Poststate;
import com.example.demo.payload.top3Company;
import com.example.demo.repository.faza;
import com.example.demo.service.postservice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;



@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    postservice postservice;
    private static final String ENTITY_NAME = "Post";


    /*-------------postStatCountbyMonth------*/
    @GetMapping("/postStatCountbyMonth")
    public TreeMap<String, Integer> STAT() throws ParseException {
        return postservice.stat();
    }

    /*-------------postStatOfOneCompanyCountbyMonth------*/
    @GetMapping("/postStatOfOneCompanyCountbyMonth/{company_Id}")
    public TreeMap<String, Integer> postStatOfOneCompanyCountbyMonth(@PathVariable String company_Id) throws ParseException {
        return postservice.postStatOfOneCompanyCountbyMonth(company_Id);
    }
    /*------------postOfCompanyInYerar------*/
    @GetMapping("/postOfCompanyInYerar/{company_Id}")
    public Integer postOfCompanyInYerar(@PathVariable String company_Id) throws ParseException {
        return postservice.postOfCompanyInYerar(company_Id);
    }

    @GetMapping("/post")
    public List<Post> findAll() {
        return postservice.findAll();
    }

    @GetMapping("/top3company")
    public List<faza> top3company() {
        return postservice.findto3();
    }

    @GetMapping("/freepost")
    public List<Post> findAllFreePosts() {
        return postservice.findAllFREEpost();
    }


    /*-------------freeand occuped post------*/
    @GetMapping("/FREEandOCUUPED")
    public TreeMap<String, Integer> FREEandOCUUPED() throws ParseException {
        return postservice.FREEandOCCUPED();
    }

    /*-----------------------------------------find by type------------------*/

    @GetMapping("/freepostByType/{type}")
    public List<Post> findAllFreePostsbytype(@PathVariable(value = "type") String type) {
        return postservice.findAllFREEpostbytype(type);
    }
/*-----------------------------------------find by city------------------*/
    @GetMapping("/freepostByCity/{city}")
    public List<Post> findAllFreePostsbycity(@PathVariable(value = "city") String city) {
        return postservice.findAllFREEpostbycity(city);
    }

    /*-----------------------------------------Post was reacted by  student-----------------*/
    @GetMapping("/postByIdStudent/{studentId}")
    public List<Post> findAllpostByIdStudent(@PathVariable(value = "studentId") String studentId) {
        return postservice.findPostesbylistids(studentId);
    }
    /*-----------------------------------------Post was not reacted yet  by  student-----------------*/
    @GetMapping("/FreepostNOOOtReactedByIdStudent/{studentId}")
    public List<Post> FreepostNOOOtReactedByIdStudent(@PathVariable(value = "studentId") String studentId) {
        return postservice.FreepostNOOOtReactedByIdStudent(studentId);
    }


    /*-----------------------------------------find by type and city------------------*/
    @GetMapping("/freepostByTypeAndCity/{type}/{city}")
    public List<Post> findAllFreePostsbyTypeandCity(@PathVariable(value = "type") String type, @PathVariable(value = "city") String city) {
        return postservice.findAllFREEpostbyTypeAndCity(type, city);
    }

    @PostMapping("/postt")
    public ResponseEntity<Post> postanewpost(@RequestBody Post post) throws ResourceNotFoundException {

        Post result = postservice.save(post);
        return ResponseEntity.ok().body(result);
    }
    // deletepost
    @DeleteMapping("/deletepost/{id}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        postservice.delete(postId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
/*--------------find post by id ----------------------*/
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostbyid(@PathVariable Integer id) throws ResourceNotFoundException {

        Post dto = postservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
/*--------------update post ------------------------*/
    @PutMapping("/poster/{id}")
    public ResponseEntity<Post> updateExperienceById(@PathVariable Integer id,  @RequestBody Post pst) throws MethodArgumentNotValidException, ResourceNotFoundException {

        pst.setId(id);
        Post test = postservice.findOne(id);
        if( test.getPoststate() == Poststate.OCCUPIED) {
            Post result = null;
            return ResponseEntity.ok().body(result);
        }
        Post result = postservice.update(pst);
        return ResponseEntity.ok().body(result);
    }
/*--------------get post by company id ------------*/
    @GetMapping("/posts/{company_Id}")
    public List<Post> getListOfPostbyCompanyId(@PathVariable String company_Id) throws ResourceNotFoundException {

        List<Post> dto = postservice.findListPostByCompanyId( company_Id);

        return dto;
    }

/*----------------- FreePostsByCompanyId-------------*/
    @GetMapping("/FreePostsByCompanyId/{company_Id}")
    public List<Post> FreePostsByCompanyId(@PathVariable String company_Id) throws ResourceNotFoundException {

        List<Post> dto = postservice.FREEPostsByCompanyId( company_Id);

        return dto;
    }
// OCCUPEDPostsByCompanyId
    @GetMapping("/OCCUPEDPostsByCompanyId/{company_Id}")
    public List<Post> OCCUPEDPostsByCompanyId(@PathVariable String company_Id) throws ResourceNotFoundException {

        List<Post> dto = postservice.OCCUPEDPostsByCompanyId( company_Id);

        return dto;
    }
}
