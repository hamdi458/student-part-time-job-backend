package com.example.demo.service;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.repository.studentRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class studentservice {
    @Autowired
    private studentRepository studentrepository;
    @Autowired
    private  postservice postservice;
    @Autowired
    private reactservice reactservice;


 /* ---------------------------------get all------------------*/
 public List<Student> findAll() {
     return studentrepository.findAll();


 }
    /*--------------------------save one ------------------*/
    public Student save(Student student) {
        return studentrepository.save(student);
    }

    /* ------------------------update student----------------- */
    public Student update(Student student) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(student.getCin())
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + student.getCin()));
        inBase.setFirstname(student.getFirstname());
        inBase.setLastname(student.getLastname());
        inBase.setCity(student.getCity());
        inBase.setNationality(student.getNationality());
        inBase.setDatebirth(student.getDatebirth());
        inBase.setPhone(student.getPhone());
        inBase.setDescripion(student.getDescripion());



        inBase = studentrepository.save(inBase);
        return inBase;
    }
    /* ------------------------studentupdateViews----------------- */
    public Student studentupdateViews(String id) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not found for this id :: " + id));
       Long a = inBase.getViews();
       long b = a+1 ;

        inBase.setViews(b);

        inBase = studentrepository.save(inBase);
        return inBase;
    }




    /* ---------------find one---------*/
    public Student findOne(String id) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("STUDENT not found for this id :: " + id));

        return inBase;
    }
    /* ---------------numberOfReactsBystudent---------*/
    public Integer numberOfReactsBystudent(String id) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("STUDENT not found for this id :: " + id));
        Integer n = inBase.getStudentreact().size();

        return n;
    }


    /* -------------------delete by id -------------------*/
    public void delete(String id) {

        studentrepository.deleteById(id);
    }



    /* --------------- studentMonyWithUs---------*/
    public double studentMonyWithUs(String id) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("STUDENT not found for this id :: " + id));
        List<React> allreacts = inBase.getStudentreact();

        List<React> acceptedReact = allreacts.stream()
                .filter(line -> Reacttype.ACCEPTED.equals(line.getReactstate()))
                .collect(Collectors.toList());

        List<Long> postIds =  acceptedReact.stream().map(sc -> sc.getPostid()).collect(Collectors.toList());
        List<Post> postsaccepted =  postservice.getListPostsByListIdis( postIds);



        long sum = 0;
        for(Post item : postsaccepted){

            // allposts.forEach(item->{
            long noOfDaysBetween = ChronoUnit.DAYS.between(item.getDateend(), item.getDatestart());
            if (noOfDaysBetween <= 0)
                noOfDaysBetween = 1;
            System.out.println("number of days "+noOfDaysBetween);
            long h = item.getStarthour().until(item.getEndhour(), ChronoUnit.HOURS);
            System.out.println("UNTIL "+ h);
            System.out.println("salaire "+ item.getSalaire());
            sum += noOfDaysBetween * h * item.getSalaire();
            System.out.println(sum);
        }
        return sum;
    }

    /* ---------------numberOfReactsBystudent---------*/
    public Integer percentageofsuccess(String id) throws ResourceNotFoundException {

        Student inBase = studentrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("STUDENT not found for this id :: " + id));
     List<React>   allreacts = inBase.getStudentreact();

        List<React> acceptedReact = allreacts.stream()
                .filter(line -> Reacttype.ACCEPTED.equals(line.getReactstate()))
                .collect(Collectors.toList());

        System.out.println("acc"+ acceptedReact.size());
        System.out.println("all"+ allreacts.size());

        double sum = (double)acceptedReact.size()/ (double) allreacts.size();
        System.out.println("summ"+ sum);
        System.out.println("*100summ"+ sum*100);
        double result = sum*100;
        Integer res = (int) Math.round(result);
        return res;
    }
    /* -------------------FIND LIST  react BY postID -------------------*/
    public List<Student> findListSTUDENTReactByPostId(long postid) {
      List<React> REACTS = reactservice.findListReactByPostId(postid);
        List<String> studentsIds =  REACTS .stream().map(sc -> sc.getStudintcin()).collect(Collectors.toList());
List<Student> STUDENT = studentrepository.findByCinIn(studentsIds);
return STUDENT;

    }

}
