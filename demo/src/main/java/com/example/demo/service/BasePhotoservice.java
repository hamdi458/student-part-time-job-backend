package com.example.demo.service;

import com.example.demo.exception.FileNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BasePhoto;


import com.example.demo.model.Photo;
import com.example.demo.repository.BasePhtoRepository;
import com.example.demo.repository.postRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class BasePhotoservice {
    @Autowired
    private BasePhtoRepository BasePhtoRepository;

/* post photo */
    public BasePhoto getJson(Long id, MultipartFile ph) throws IOException, ResourceNotFoundException {


        String fileName = StringUtils.cleanPath(ph.getOriginalFilename());

        BasePhoto userJson = new BasePhoto();
        ObjectMapper objectMapper = new ObjectMapper();
        userJson.setId(id);
        userJson.setPhoto(ph.getBytes());

        userJson.setPhotoName(fileName);
        userJson.setPhotoType(ph.getContentType());

        BasePhtoRepository.save(userJson);



        return userJson;

    }

/* get photo */
    public Optional<BasePhoto> getone(long id) {
        Optional<BasePhoto> a = BasePhtoRepository.findById(id);
                return a;
    }
}
