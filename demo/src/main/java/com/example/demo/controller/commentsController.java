package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Comments;
import com.example.demo.model.React;
import com.example.demo.service.commentsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class commentsController {
    @Autowired
    commentsservice commentsservice;
    private static final String ENTITY_NAME = "Comments";

/*------------------ Note of student by comments Nots -------*/
    @GetMapping("/StudentNote/{studentid}")
    public double StudentNote(@PathVariable String studentid) {
        return commentsservice.StudentNote(studentid);
    }
// get CommentsAndNotes
    @GetMapping("/CommentsAndNotes")
    public List<Comments> findAll() {
        return commentsservice.findAll();
    }
// post CommentsAndNotes
    @PostMapping("/CommentsAndNotes")
    public Comments postComments(@RequestBody Comments Comments) throws ResourceNotFoundException {

        Comments result = commentsservice.save(Comments);
        return result;
    }
// delete CommentsAndNotes
    @DeleteMapping("/CommentsAndNotes/{id}")
    public Map<String, Boolean> deleteComments(@PathVariable(value = "id") Long skillId)
            throws ResourceNotFoundException {
        commentsservice.delete(skillId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
// get CommentsAndNotes by id
    @GetMapping("/CommentsAndNotes/{id}")
    public ResponseEntity<Comments> getskillbyid(@PathVariable Integer id) throws ResourceNotFoundException {

        Comments dto = commentsservice.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
// put CommentsAndNotes
    @PutMapping("/CommentsAndNotes/{id}")
    public ResponseEntity<Comments> updateSkillById(@PathVariable Integer id,  @RequestBody Comments Comments) throws MethodArgumentNotValidException, ResourceNotFoundException {

        Comments.setId(id);
        Comments result =commentsservice.update(Comments);
        return ResponseEntity.ok().body(result);
    }

    // listCommentsAndNotesbyStudentId
    @GetMapping("/listCommentsAndNotesbyStudentId/{studentid}")
    public List<Comments> listCommentsbyPStudentId(@PathVariable String studentid)
    {
        return commentsservice.findListCommentsByStudentId(studentid);
    }

}
