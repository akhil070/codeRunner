package com.cvr.it.coderunner.service;


import java.util.List;
import java.util.stream.Collectors;

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
    
    @Autowired
    private VisionService visionService;
    
    public static final String FAILED = "HAS_ONE_FAILED_IMAGE";

    public String processImage(List<MultipartFile> files) throws Exception {
        return files.stream().map(file -> {
            try {
                return visionService.detectText(file);
            } catch (Exception e) {
                return FAILED;
            }
        }).collect(Collectors.joining("\n"));
//        return textractService.processImage(file);
    }

}
