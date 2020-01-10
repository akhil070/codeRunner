package com.cvr.it.coderunner.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Service
@Slf4j
public class ImageService {
    
    @Autowired
    private TextractService textractService;

    public String processImage(MultipartFile file) {
        return textractService.processImage(file);
    }

}
