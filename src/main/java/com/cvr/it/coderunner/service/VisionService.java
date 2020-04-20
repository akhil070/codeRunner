package com.cvr.it.coderunner.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
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
    private ResourceLoader resourceLoader;
    
    @Autowired
    private CloudVisionTemplate template;
    
    @Autowired
    private TerminalService terminalService;
    
    public String detectText(MultipartFile file) throws Exception, IOException {
        
//        AnnotateImageResponse response = template.analyzeImage(file.getResource(), Feature.Type.TEXT_DETECTION);
        String extension = file.getContentType().split("/")[1];
        String path = String.format("image-%s.%s", new Random().nextInt(), extension);
        
        file.transferTo(Paths.get(path));
        
        terminalService
                .runCommand(Arrays.asList("/bin/bash", "-c", "source clean/bin/activate && python3 main.py " + path));
    
//        terminalService
//                .runCommand(Arrays.asList("/bin/bash", "-c", "python3 main.py " + path));
        
        MultipartFile multipartFile = new MockMultipartFile(String.format("code.%s", extension),
                                                            new FileInputStream(new File("cleaned/" + path)));
        
        String textFromImage = this.template.extractTextFromImage(multipartFile.getResource());
        return textFromImage;
        
    }
    
}
