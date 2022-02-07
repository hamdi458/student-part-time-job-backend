package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Compte;

import com.example.demo.repository.compteRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.compteService;
import com.example.demo.service.experienceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:58893")
@RestController
@RequestMapping("/api")
public class compteController {

    @Autowired
    compteService service;



    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    compteRepository compteRepository;
    private static final String ENTITY_NAME = "Compte";
// create new compte
    @PostMapping("/compte")
    public ResponseEntity<Compte> postCompte(@RequestBody Compte Compte) throws MessagingException {
        Compte existingUser = compteRepository.findByEmailIgnoreCase(Compte.getEmail());


        Integer randomNumber = ( int )( Math.random() * 9999 );

        if( randomNumber <= 1000 ) {
            randomNumber = randomNumber + 1000;
        }
        Compte.setVerif(randomNumber);


        if(existingUser != null)
        {
            Compte = null;
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        /*neww---*/

        mailMessage.setTo(Compte.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("Stdents.part.time.job0@gmail.com");
        mailMessage.setText("Hi "+Compte.getName() +" your confirmation code is : "+randomNumber);



        emailSenderService.sendEmail(mailMessage);

        Compte result = service.save(Compte);
        return ResponseEntity.ok().body(result);
    }
// get compte by id
    @GetMapping("/compte/{id}")
    public ResponseEntity<Compte> getComptebyid(@PathVariable String id) throws ResourceNotFoundException {

        Compte dto = service.findOne(id);

        return ResponseEntity.ok().body(dto);
    }
// verif if we have already a compte
    @GetMapping("/verif/{id}")
    public Integer verif(@PathVariable String id) throws ResourceNotFoundException {

        Compte dto = service.findOne(id);

        return dto.getVerif();
    }
// get compte by id

    @GetMapping("/compte/{id}/{password}")
    public ResponseEntity<Compte> getComptebyidandpassword(@PathVariable String id, @PathVariable String password) throws ResourceNotFoundException {

        Compte dto = service.findbEmailAndPassword(id, password);

        return ResponseEntity.ok().body(dto);
    }
// delete compte
    @DeleteMapping("/deletecompte/{id}")
    public Map<String, Boolean> deleteReact(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        service.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
