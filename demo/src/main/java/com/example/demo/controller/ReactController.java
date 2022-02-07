package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.React;
import com.example.demo.service.postservice;
import com.example.demo.service.reactservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class ReactController {
    @Autowired
    reactservice reactservice;
    private static final String ENTITY_NAME = "React";


    @GetMapping("/sumallREACT")
    public Integer findAll() {
        return reactservice.findAll();
    }

    /*-------------stattest------*/
    @GetMapping("/reactStatCountbyMonth")
    public TreeMap<String, Integer> STAT() throws ParseException {
        return reactservice.stat();
    }

// new react
    @PostMapping("/react")
    public React postReact(@RequestBody React react) throws ResourceNotFoundException {


        React result = reactservice.save(react);

        return result;
    }
// react by id
    @PutMapping("/react/{id}")
    public ResponseEntity<React> updateReactById(@PathVariable Integer id, @RequestBody React react) throws MethodArgumentNotValidException, ResourceNotFoundException {

        react.setId(id);
        React result = reactservice.update(react);
        return ResponseEntity.ok().body(result);
    }
// delete react
    @DeleteMapping("/react/{id}")
    public Map<String, Boolean> deleteReact(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        reactservice.delete(postId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

/*-----------------------delete by postid and sutudintcode----------*/
    @DeleteMapping("/react/{postid}/{studentid}")
    public Map<String, Boolean> deleteReact(@PathVariable(value = "postid") Long postId, @PathVariable(value = "studentid") String studentid)
            throws ResourceNotFoundException {
      long result =  reactservice.deleteByPostidandStudentid(postId,studentid);
        Map<String, Boolean> response = new HashMap<>();
      if (result == 1)


        response.put("deleted", Boolean.TRUE);
      else
          response.put("deleted", Boolean.FALSE);

        return response;
    }
    /* put react by postid and studentid-----------*/
    @PutMapping("/reactbyPostIDandStudentid/{postid}/{studentid}")
    public ResponseEntity<React> updateReactById(@PathVariable(value = "postid") Long postId, @PathVariable(value = "studentid") String studentid,@RequestBody React react) throws ResourceNotFoundException {
      React newreact = reactservice.GetOneByPostidandStudentid(postId,studentid);
        newreact.setPostid(postId);
        newreact.setStudintcin(studentid);
        react.setId(newreact.getId());
        React result = reactservice.updatebyPostidandStudentid(react,newreact);
        return ResponseEntity.ok().body(result);
    }


    /*-----------------------GET by postid and sutudintcode----------*/
    @GetMapping("/reactByPostIdAndStudentId/{postid}/{studentid}")
    public ResponseEntity<React> reactByPostIdAndStudentId(@PathVariable(value = "postid") Long postId, @PathVariable(value = "studentid") String studentid)
            throws ResourceNotFoundException {
        React dto = reactservice.GetOneByPostidandStudentid(postId,studentid);


        return ResponseEntity.ok().body(dto);
    }
// get by id
    @GetMapping("/react/{id}")
    public ResponseEntity<React> getReactbyid(@PathVariable Integer id) throws ResourceNotFoundException {

        React dto = reactservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }

    // reactsbyPostId
    @GetMapping("/reactsbyPostId/{postid}")
    public List<React> reactsbyPostId(@PathVariable Integer postid)
    {
        return reactservice.findListReactByPostId(postid);
    }
    // reactsbyStudentId
    @GetMapping("/reactsbyStudentId/{studentid}")
    public List<React> reactsbyPStudentId(@PathVariable String studentid)
    {
        return reactservice.findListReactByStudentId(studentid);
    }
    // reactsIntresstedForCompany
    @GetMapping("/reactsIntresstedForCompany/{companyid}")
    public List<React> reactsIntresstedForCompany(@PathVariable String companyid)
    {
        return reactservice.reactsIntresstedForCompany(companyid);
    }


// acceptReactOrNot
    @GetMapping("/acceptReactOrNot/{postid}/{studentid}")
    public boolean reactsIntresstedForCompany(@PathVariable Integer postid, @PathVariable String studentid) throws ResourceNotFoundException {
        return reactservice.acceptReactOrNot(postid,studentid);
    }

  //  totalreactsbyPostId
    @GetMapping("/totalreactsbyPostId/{postid}")
    public Integer totalreactsbyPostId(@PathVariable Integer postid)
    {
        return reactservice.totalreactsbyPostId(postid);
    }
}
