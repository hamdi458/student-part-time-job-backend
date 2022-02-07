package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.Poststate;
import com.example.demo.model.React;
import com.example.demo.model.Reacttype;
import com.example.demo.repository.reactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class reactservice {
    @Autowired
    private reactRepository reactRepository;
    @Autowired
    postservice postservice;




    /* ---------------------------------get all------------------*/
    public Integer findAll() {

        List<React> r = reactRepository.findAll();
        return r.size();
    }
    /*----------------statistique test-------------------------------*/
    public TreeMap<String, Integer> stat() throws ParseException {
        List<String> allDates = new ArrayList<>();

        LocalDate maxDate = LocalDate.now();

        String A = maxDate.toString();

        SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(A));


        TreeMap<String, Integer> my_dict = new TreeMap<String, Integer>(Collections.reverseOrder());
        for (int i = 1; i <= 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            List<React> reacts = reactRepository.findByMatchMonthAndYear(month_name1, Reacttype.ACCEPTED.getState());
            Integer count = reacts.size();

            String jareb = month_name1.split("-")[1];
            Integer  foo = Integer.parseInt(jareb);
            String name = getMonth(foo);
            my_dict.put(name, count);
            allDates.add(month_name1);
            cal.add(Calendar.MONTH, -1);
        }
        System.out.println(allDates);
        return my_dict;
    }
    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    /* -------------------FIND LIST  react BY postID -------------------*/
    public List<React> findListReactByPostId(long post_id) {
        return  reactRepository.findByPostid(post_id);

    }
    /* -------------------totalreactsbyPostId -------------------*/
    public Integer totalreactsbyPostId(long post_id) {
       List<React> a = this.findListReactByPostId(post_id);
        return  a.size();

    }


    /* -------------------FIND LIST  react BY studentID -------------------*/
    public List<React> findListReactByStudentId(String student_id) {
        return  reactRepository.findByStudintcinnnn(student_id);

    }


    /*********find one ************/
    public React findOne(long id) throws ResourceNotFoundException {

        React inBase = reactRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("REACT not found for this id :: " + id));

        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {

        reactRepository.deleteById(id);
    }



    /* -------------------delete by list Reactids -------------------*/
    public Integer  deleteByListOfReactids(List<Integer> ids) {

        Integer aa =  reactRepository.deleteByIdIn(ids);
        return aa;
    }
    /* -------------------delete by Post id  and student id-------------------*/
    public Long deleteByPostidandStudentid(long postid, String studentid) {

      Long aa = reactRepository.deleteByPostidAndStudintcinnnn(postid,studentid);

        return aa;
    }
    /* -------------------GET by Post id  and student id-------------------*/
    public React GetOneByPostidandStudentid(long postid, String studentid) {

     React inBase =   reactRepository.getByPostidAndStudintcinnnn(postid,studentid);
        return inBase;
    }

    /* ------------------------update react state----------------- */
    public React update(React react) throws ResourceNotFoundException {

        React inBase = reactRepository.findById(react.getId())
                .orElseThrow(() -> new ResourceNotFoundException("react not found for this id :: " + react.getId()));
        inBase.setReactstate(react.getReactstate());
        if(react.getReactstate()==Reacttype.ACCEPTED) {
             List<React> ALLreacts = findListReactByPostId(inBase.getPostid());
             ALLreacts.remove(inBase);
             ALLreacts.forEach(item -> item.setReactstate(Reacttype.REFUSED));
             Post thepost = postservice.findOne(inBase.getPostid());
             thepost.setPoststate(Poststate.OCCUPIED);

        }
        return inBase;
    }

    /* ------------------------update react state by post id and student id----------------- */
    public React updatebyPostidandStudentid(React react, React inBase) throws ResourceNotFoundException {



        inBase.setReactstate(react.getReactstate());
        if(react.getReactstate()==Reacttype.ACCEPTED) {
            List<React> ALLreacts = findListReactByPostId(inBase.getPostid());
            ALLreacts.remove(inBase);
            ALLreacts.forEach(item -> item.setReactstate(Reacttype.REFUSED));
            Post thepost = postservice.findOne(inBase.getPostid());
            thepost.setPoststate(Poststate.OCCUPIED);

        }
        return inBase;
    }

    /*--------------------------save one ------------------*/
    public React save(React ps) throws ResourceNotFoundException {

        return reactRepository.save(ps);
    }

    /*----------------------------------reactsIntresstedForCompany -------------------*/
    public List<React> reactsIntresstedForCompany(String company_id) {
        List<Post> allpostes = postservice.findListPostByCompanyId(company_id);
        System.out.println("++++++++++++++++++++++++++allpostes+++++////////"+allpostes.size());



        List<React> resu = allpostes
                .stream().flatMap(item -> item.getPostreact().stream())
                .collect(Collectors.toList());
        System.out.println("++++++++++++++++++++++++++react1111111+++++////////"+resu.size());

        List<React> result = resu.stream()                // convert list to stream
                .filter(line -> Reacttype.INTERESTED.equals(line.getReactstate()))     // we dont like mkyong
                .collect(Collectors.toList());
        System.out.println("++++++++++++++++++++++++++react2222222+++++////////"+result.size());
        return  result;

    }

// verif react
    public boolean acceptReactOrNot(Integer postid,String studentid) throws ResourceNotFoundException {
        List<React> allreacts = this.findListReactByStudentId(studentid);

        List<React> acceptedReact = allreacts.stream()
                .filter(line -> Reacttype.ACCEPTED.equals(line.getReactstate()))
                .collect(Collectors.toList());

        List<Long> postIds =  acceptedReact.stream().map(sc -> sc.getPostid()).collect(Collectors.toList());
        List<Post> ALLpostes = postservice.getListPostsByListIdis(postIds);
        Post post = postservice.findOne(postid);
        for (Post item: ALLpostes
             ) {
            if(( post.getDatestart().isAfter(item.getDatestart()) && post.getDatestart().isBefore(item.getDateend())) || ( post.getDateend().isAfter(item.getDatestart()) && post.getDateend().isBefore(item.getDateend()))
            || (post.getDatestart().isEqual(item.getDatestart()) && post.getDateend().isEqual(item.getDateend())))
            {


                if(( post.getStarthour().isAfter(item.getStarthour()) && post.getStarthour().isBefore(item.getEndhour())) || ( post.getEndhour().isAfter(item.getStarthour()) && post.getEndhour().isBefore(item.getEndhour()))
                    || (post.getStarthour() == item.getStarthour() && post.getEndhour() == item.getEndhour())) { return false;}

            }
        }
return true;

    }

}
