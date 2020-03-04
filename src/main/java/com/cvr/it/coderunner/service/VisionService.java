package com.cvr.it.coderunner.service;

import java.io.IOException;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 17/01/20
 **/

@Service
@Slf4j
public class VisionService {
    
    @Autowired
    private CloudVisionTemplate template;
    
    public String detectText(MultipartFile file) throws Exception, IOException {
        
        AnnotateImageResponse response = template.analyzeImage(file.getResource(), Feature.Type.TEXT_DETECTION);
        String textFromImage = this.template.extractTextFromImage(file.getResource());
        return textFromImage;
        
    }
    
}
