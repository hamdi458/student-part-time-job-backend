package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.model.Comments;
import com.example.demo.model.Company;
import com.example.demo.model.React;
import com.example.demo.repository.commentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class commentsservice {
    @Autowired
    private commentsRepository commentsRepository;
    @Autowired
    private companyservice companyservice;

    /* ---------------------------------get all------------------*/
    public List<Comments> findAll() {
        return commentsRepository.findAll();


    }
    /*--------------------------save one ------------------*/
    public Comments save(Comments sk) throws ResourceNotFoundException {

        Company comp = companyservice.findOne(sk.getCompanyid());
        sk.setCompanyname(comp.getName());
        return commentsRepository.save(sk);
    }

    /* ------------------------update Comments----------------- */
    public Comments update(Comments sk) throws ResourceNotFoundException {

        Comments inBase = commentsRepository.findById(sk.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comments not found for this id :: " + sk.getId()));
        inBase.setComment(sk.getComment());
        inBase.setNote(sk.getNote());


        inBase = commentsRepository.save(inBase);
        return inBase;
    }
    /* ---------------find one---------*/
    public Comments findOne(long id) throws ResourceNotFoundException {

        Comments inBase = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comments not found for this id :: " + id));


        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {

        commentsRepository.deleteById(id);
    }

    /* -------------------FIND LIST  comments BY studentID -------------------*/
    public List<Comments> findListCommentsByStudentId(String student_id) {
        return  commentsRepository.findByStuudentid(student_id);

    }

    /* -------------------FIND LIST  comments BY studentID -------------------*/
    public double StudentNote(String student_id) {
        List<Comments> allcomments=   commentsRepository.findByStuudentid(student_id);
        int sum = allcomments.stream().mapToInt(o -> o.getNote()).sum();
        int baze = allcomments.size();
        double result = sum/(double) baze;
        return result;

    }
}
