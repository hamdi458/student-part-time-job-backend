package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.payload.top3Company;
import com.example.demo.repository.faza;
import com.example.demo.repository.postRepository;
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
public class postservice {

    @Autowired
    private postRepository postRepository;
    @Autowired
    private companyservice companyservice;
    @Autowired
    private reactservice  reactservice;

    /*----------------statistique test-------------------------------*/
    public TreeMap<String, Integer> stat() throws ParseException {
        List<String> allDates = new ArrayList<>();

        LocalDate maxDate = LocalDate.now();
        System.out.println("++++++++++++++++++++++++++DATE+++++////////"+maxDate);
        String A = maxDate.toString();


        System.out.println("++++++++++++++++++++yyyy-MM++++++DATE string+++++////////"+A);
        SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(A));
        System.out.println("++++++++++++++++++++++++++DATE parseee+++++////////"+monthDate.parse(A));

        TreeMap<String, Integer> my_dict = new TreeMap<String, Integer>(Collections.reverseOrder());
        for (int i = 1; i <= 12; i++) {
           String month_name1 = monthDate.format(cal.getTime());
            List<Post> posts = postRepository.findByMatchMonthAndYear(month_name1);
            System.out.println("++++++++++++++++++++++++++the month nameeee++////////"+month_name1);
            Integer count = posts.size();
            /* jdiid*/
            String jareb = month_name1.split("-")[1];
          Integer  foo = Integer.parseInt(jareb);
          String name = getMonth(foo);
            System.out.println("+++++++++++++++++++++++++jarebbb||||"+name);

          //  my_dict.put(month_name1, count);
            my_dict.put(name, count);
            allDates.add(month_name1);
            cal.add(Calendar.MONTH, -1);
        }
        System.out.println(allDates);
        return my_dict;
    }


    /*----------------statistique testfor one company-------------------------------*/
    public TreeMap<String, Integer> postStatOfOneCompanyCountbyMonth(String companyid) throws ParseException {
        List<String> allDates = new ArrayList<>();

        LocalDate maxDate = LocalDate.now();
        System.out.println("++++++++++++++++++++++++++DATE+++++////////"+maxDate);
        String A = maxDate.toString();


        System.out.println("++++++++++++++++++++yyyy-MM++++++DATE string+++++////////"+A);
        SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(A));
        System.out.println("++++++++++++++++++++++++++DATE parseee+++++////////"+monthDate.parse(A));

        TreeMap<String, Integer> my_dict = new TreeMap<String, Integer>(Collections.reverseOrder());
        for (int i = 1; i <= 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            List<Post> posts = postRepository.findByMatchMonthAndYearAndCompanyId(month_name1,companyid);
            System.out.println("++++++++++++++++++++++++++the month nameeee++////////"+month_name1);
            Integer count = posts.size();
            /* jdiid*/
            String jareb = month_name1.split("-")[1];
            Integer  foo = Integer.parseInt(jareb);
            String name = getMonth(foo);
            System.out.println("+++++++++++++++++++++++++jarebbb||||"+name);

            //  my_dict.put(month_name1, count);
            my_dict.put(name, count);
            allDates.add(month_name1);
            cal.add(Calendar.MONTH, -1);
        }
        System.out.println(allDates);
        return my_dict;
    }



    /*----------------statistique testfor one company-------------------------------*/
    public Integer postOfCompanyInYerar(String companyid) throws ParseException {


        LocalDate maxDate = LocalDate.now();
        System.out.println("++++++++++++++++++++++++++DATE+++++////////"+maxDate);
        String A = maxDate.toString();


        System.out.println("++++++++++++++++++++yyyy-MM++++++DATE string+++++////////"+A);
        SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate.parse(A));
        System.out.println("++++++++++++++++++++++++++DATE parseee+++++////////"+monthDate.parse(A));

        TreeMap<String, Integer> my_dict = new TreeMap<String, Integer>(Collections.reverseOrder());
        Integer sum = 0;
        for (int i = 1; i <= 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            List<Post> posts = postRepository.findByMatchMonthAndYearAndCompanyId(month_name1,companyid);
            System.out.println("++++++++++++++++++++++++++the month nameeee++////////"+month_name1);
            Integer count = posts.size();
            sum = sum + count;


            cal.add(Calendar.MONTH, -1);
        }

        return sum;
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }


    /* ---------------------------------get all------------------*/
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    /* ---------------------------------get top3------------------*/
    public  List<faza> findto3() {

        List<faza> obj = postRepository.getto3();

        return obj;
    }



    /* ---------------------------------get all free post------------------*/
    public List<Post> findAllFREEpost() {
      /*  List<Post> ALL = postRepository.findAll();
        List<Post> result1 = ALL.stream()                        // Convert to steam
                .filter(x -> Poststate.FREE.equals(x.getPoststate()))        // we want "jack" only
                .collect(Collectors.toList());
        return result1;*/
        List<Post> all =  postRepository.findByPoststateOrderByPostedateDesc(Poststate.FREE);
        return all;
    }



    public TreeMap<String, Integer> FREEandOCCUPED(){
        TreeMap<String, Integer> my_dict = new TreeMap<String, Integer>(Collections.reverseOrder());
        List<Post> allfree =  postRepository.findByPoststateOrderByPostedateDesc(Poststate.FREE);
        List<Post> alloccuped =  postRepository.findByPoststateOrderByPostedateDesc(Poststate.OCCUPIED);
        my_dict.put("FREE", allfree.size());
        my_dict.put("OCCUPIED", alloccuped.size());
        return my_dict;
    }

    /* ---------------------------------get all free post by type------------------*/
    public List<Post> findAllFREEpostbytype(String type) {

        List<Post> all =  postRepository.findByPoststateAndTypeOrderByPostedateDesc(Poststate.FREE, type);
        return all;
    }
    /* ---------------------------------get all free post by city------------------*/
    public List<Post> findAllFREEpostbycity(String city) {

        List<Post> all =  postRepository.findByPoststateAndCityOrderByPostedateDesc(Poststate.FREE, city);
        return all;
    }

    /* ---------------------------------get postes racted by student ------------------*/
    public List<Post> findPostesbylistids(String studentid) {
        List<React> reacts = reactservice.findListReactByStudentId(studentid);

        List<Long> postIds =  reacts.stream().map(sc -> sc.getPostid()).collect(Collectors.toList());

        List<Post> all =  postRepository.findByIdIn(postIds);
        return all;
    }
    /*---------------------------------get list posts by list idis-----------*/
    public List<Post> getListPostsByListIdis(List<Long> postIds) {

        List<Post> all =  postRepository.findByIdIn(postIds);
        return all;
    }


    /* ---------------------------------Post not Reacted by student ------------------*/
    public List<Post> FreepostNOOOtReactedByIdStudent(String studentid) {
        List<Post> Studentreacts = this.findPostesbylistids(studentid);
        List<Post> AllFreePost = this.findAllFREEpost();


        List<Post> differences = AllFreePost.stream()
                .filter(element -> ! Studentreacts.contains(element))
                .collect(Collectors.toList());



        return differences;
    }




    /* ---------------------------------get all free post by type and city------------------*/
    public List<Post> findAllFREEpostbyTypeAndCity(String type, String city) {

        List<Post> all =  postRepository.findByPoststateAndTypeAndCityOrderByPostedateDesc(Poststate.FREE, type,city);
        return all;
    }



    /*--------------------------save one ------------------*/
    public Post save(Post ps) throws ResourceNotFoundException {
        Company comp = companyservice.findOne(ps.getCompanyid());
        ps.setCompanyname(comp.getName());
        System.out.println("++++++++++++++++++++++++++++++++////////"+ps.getSalaire());
        return postRepository.save(ps);
    }

    /* ------------------------update post----------------- */
    public Post update(Post pos) throws ResourceNotFoundException {

        Post inBase = postRepository.findById(pos.getId())
                .orElseThrow(() -> new ResourceNotFoundException("post not found for this id :: " + pos.getId()));
        inBase.setName(pos.getName());

        inBase.setDescription(pos.getDescription());
        inBase.setAdress(pos.getAdress());
        inBase.setCity(pos.getCity());
        inBase.setSalaire(pos.getSalaire());
        inBase.setPostedate(pos.getPostedate());
        inBase.setDatestart(pos.getDatestart());
        inBase.setDateend(pos.getDateend());
        inBase.setStarthour(pos.getStarthour());
        inBase.setEndhour(pos.getEndhour());

        inBase.setPoststate(pos.getPoststate());
        inBase = postRepository.save(inBase);
        return inBase;
    }
/*********find one ************/
    public Post findOne(long id) throws ResourceNotFoundException {
        System.out.println("++++++++++++++++++++++++++++++++");
        Post inBase = postRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("post not found for this id :: " + id));
        System.out.println("********************************************");
        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(long id) {
      List<React> allreact = reactservice.findListReactByPostId(id);
        System.out.println("**********************************sizeeeee**********"+allreact.size());
        List<Long> reactIds =  allreact.stream().map(sc -> sc.getId()).collect(Collectors.toList());
        System.out.println("**********************************sizeint**********"+allreact.size());
        List<Integer> ids = reactIds.stream()
                .mapToInt(Long::intValue)
                .boxed()
                .collect(Collectors.toList());
       Integer A = reactservice.deleteByListOfReactids(ids);
        System.out.println("**********************************AAA**********"+A);
        postRepository.deleteById(id);
    }
    /* -------------------FIND LIST  POST BY COMPANY ID -------------------*/
    public List<Post> findListPostByCompanyId(String company_id) {
        return  postRepository.findByCompanyid(company_id);

    }

    /*------------------FREEPostsByCompanyId----------------------*/
    public List<Post> FREEPostsByCompanyId(String company_id) {
        List<Post> allpostes = this.findListPostByCompanyId(company_id);

        List<Post> result = allpostes.stream()
                .filter(line -> Poststate.FREE.equals(line.getPoststate()))
                .collect(Collectors.toList());
        return result;

    }


   /*------------------ OCCUPEDPostsByCompanyId-------------*/

    public List<Post> OCCUPEDPostsByCompanyId(String company_id) {
        List<Post> allpostes = this.findListPostByCompanyId(company_id);

        List<Post> result = allpostes.stream()
                .filter(line -> Poststate.OCCUPIED.equals(line.getPoststate()))
                .collect(Collectors.toList());
        return result;

    }

}
