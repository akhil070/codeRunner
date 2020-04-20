package com.cvr.it.coderunner.controller;

import static com.cvr.it.coderunner.service.ImageService.FAILED;

import java.util.List;

import com.cvr.it.coderunner.service.ImageService;
import com.google.rpc.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
    @PostMapping("/upload")
    private ResponseEntity<String> getProcessedImage(@RequestParam(name = "file") List<MultipartFile> files) throws Exception {
        if (files.size() == 0) {
            return ResponseEntity.badRequest().body("no files added");
        }
        String text = imageService.processImage(files);
        if (text.contains(FAILED)) {
            return ResponseEntity.badRequest().body("Failed to process one or more images");
        }
        return ResponseEntity.ok().body(imageService.processImage(files));
    }
    
    
}
