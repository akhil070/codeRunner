package com.cvr.it.coderunner.controller;

import com.cvr.it.coderunner.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Controller
@RequestMapping("/image")
@Slf4j
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    @PostMapping("/upload")
    private ResponseEntity<String> getProcessedImage(@RequestParam(name = "file") MultipartFile file) throws Exception {
        return ResponseEntity.ok().body(imageService.processImage(file));
    }
    
    
}
