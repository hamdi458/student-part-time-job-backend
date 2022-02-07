package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.model.Formation;
import com.example.demo.model.Post;
import com.example.demo.model.Poststate;
import com.example.demo.repository.companyRepository;
import com.example.demo.repository.formationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class companyservice {

    @Autowired
    private companyRepository companyRepository;
    @Autowired
    private postservice postservice;

    /* ---------------------------------get all------------------*/
    public List<Company> findAll() {
        return companyRepository.findAll();


    }
    /*--------------------------save one ------------------*/
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    /* ------------------------update formationills----------------- */
    public Company update(Company company) throws ResourceNotFoundException {

        Company inBase = companyRepository.findById(company.getId())
                .orElseThrow(() -> new ResourceNotFoundException("company not found for this id :: " + company.getId()));
        inBase.setName(company.getName());
        inBase.setAdress(company.getAdress());
        inBase.setDescription(company.getDescription());
        inBase.setCity(company.getCity());
        inBase.setPhone(company.getPhone());

        inBase = companyRepository.save(inBase);
        return inBase;
    }
    /* ---------------find one---------*/
    public Company findOne(String id) throws ResourceNotFoundException {

        Company inBase = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("COMPANY not found for this id :: " + id));

        return inBase;
    }
    /* -------------------delete by id -------------------*/
    public void delete(String id) {

        companyRepository.deleteById(id);
    }


    /* ---------------AVGsalaireCompany---------*/
    public Integer AVGsalaireCompany(long idpost) throws ResourceNotFoundException {

        Post post = postservice.findOne(idpost);
        List<Post> allposts = postservice.findListPostByCompanyId( post.getCompanyid());
        /* sum into lists */
        double sum = allposts.stream().mapToDouble(o ->  o.getSalaire()).sum();
        Integer a =  allposts.size();
        double result = sum/a;
        Integer res = (int) Math.round(result);


        return res;
    }


/*----------number of student acccepted by company -------------------*/
    public Integer NuberofACCEPTEDbyCompany(long idpost) throws ResourceNotFoundException {

        Post post = postservice.findOne(idpost);
        List<Post> allposts = postservice.findListPostByCompanyId( post.getCompanyid());

        List<Post> result = allposts.stream()
                .filter(line -> Poststate.OCCUPIED.equals(line.getPoststate()))
                .collect(Collectors.toList());

        return result.size();
    }
/*---------- Number of Company Post ------------------*/

    public Integer SUMpostofCompany(long idpost) throws ResourceNotFoundException {

        Post post = postservice.findOne(idpost);
        List<Post> allposts = postservice.findListPostByCompanyId( post.getCompanyid());



        return allposts.size();
    }


    /* ---------------AVGsalaireCompanyByAllCompanys---------*/
    public Integer AVGsalaireCompanyByAllCompanys(String companyid) throws ResourceNotFoundException {


        List<Post> allposts = postservice.findListPostByCompanyId(companyid);

        double sum = allposts.stream().mapToDouble(o ->  o.getSalaire()).sum();

        System.out.println(sum+"----------------------------ourcompanysum");


        //------RESULT
        double RESULT= sum/allposts.size() * 100;
        System.out.println(RESULT+"------------------------------RESULT");
        Integer res = (int) Math.round(RESULT);


        return res;
    }

/*---------------------- NuberofACCEPTEDbyCompanyBYALLOTHERCOMPANY----------------*/

    public Integer NuberofACCEPTEDbyCompanyBYALLOTHERCOMPANY(String companyid) throws ResourceNotFoundException {


        List<Post> allposts = postservice.findListPostByCompanyId(companyid);

        List<Post> result = allposts.stream()
                .filter(line -> Poststate.OCCUPIED.equals(line.getPoststate()))
                .collect(Collectors.toList());

        Integer ourcompanyAccepted =  result.size();
        System.out.println(ourcompanyAccepted+"------------------------------ourcompanyAccepted");

        // all company
        List<Post> allcompanyssposts = postservice.findAll();

        List<Post> allcompanyresult = allcompanyssposts.stream()
                .filter(line -> Poststate.OCCUPIED.equals(line.getPoststate()))
                .collect(Collectors.toList());

        Integer ALLcompanyAccepted =  allcompanyresult.size();
        System.out.println(ALLcompanyAccepted+"------------------------------ALLcompanyAccepted");
        //result
        double d = (double)ourcompanyAccepted / (double) ALLcompanyAccepted;
        System.out.println(d+"------------------------------RESULT");
        double bahta = d * 100;
        Integer res = (int) Math.round(bahta);
        return res;

    }

    /* ------------------ HoureWorKCompanyByOtherCompany -------------*/

    public Integer HoureWorKCompanyByOtherCompany(String companyid) {
// our company
        List<Post> allposts = postservice.findListPostByCompanyId(companyid);
        System.out.println("------------------he is here ----------");
        long sum = 0;
        for(Post item : allposts){
            System.out.println("------------------d5al----------");

            long noOfDaysBetween = ChronoUnit.DAYS.between(item.getDatestart(), item.getDateend());
            if (noOfDaysBetween == 0)
                noOfDaysBetween = 1;
            System.out.println("9adech men youm our"+noOfDaysBetween);
           // long h = ChronoUnit.HOURS.between(item.getEndhour(), item.getStarthour());
            long h = item.getStarthour().until(item.getEndhour(), ChronoUnit.HOURS);
                System.out.println("9adech men se3a our"+ h);
         sum += noOfDaysBetween * h;
            System.out.println("sum  our"+ sum);
            System.out.println(sum);
        }
        System.out.println(sum+"------------------------------oursum");
// other company
        List<Post> allotherposts = postservice.findAll();
        long sumallother = 0;
        for(Post item : allotherposts){
            // allposts.forEach(item->{
            long noOfDaysBetween = ChronoUnit.DAYS.between(item.getDatestart(), item.getDateend());
            System.out.println("9adech men youm other"+noOfDaysBetween);
            if (noOfDaysBetween == 0)
            { noOfDaysBetween = 1;}
           // long h = ChronoUnit.HOURS.between(item.getEndhour(), item.getStarthour());
            double h = item.getStarthour().until(item.getEndhour(), ChronoUnit.HOURS);
            System.out.println("9adech men se3a other"+ h);
            sumallother+= noOfDaysBetween * h;
        }
        System.out.println("------------------------------sumallother "+sumallother);
        // result
        double result = (double)sum / (double)sumallother * 100;
        System.out.println("---------------------sum---"+sum+" --alo athoer "+sumallother+"--le result final "+result);
        Integer res = (int) Math.round(result);
        return res;

    }



}
