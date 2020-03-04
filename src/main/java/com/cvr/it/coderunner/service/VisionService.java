package com.cvr.it.coderunner.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.amazonaws.services.textract.model.BadDocumentException;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
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
