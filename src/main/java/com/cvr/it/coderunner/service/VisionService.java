package com.cvr.it.coderunner.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import com.amazonaws.services.textract.model.BadDocumentException;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
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
    
    ImageAnnotatorClient client = null;
    
    @PostConstruct
    public void init() {
        try {
            client = ImageAnnotatorClient.create();
            log.info("GCP options initialized successfully");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    
    public String detectText(MultipartFile file) throws Exception, IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        
        Image img = Image.newBuilder().setContent(ByteString.copyFrom(file.getBytes())).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);
        
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();
        
        String predicted = null;
        
        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                log.error("Error: {}\n", res.getError().getMessage());
                throw new BadDocumentException(res.getError().getMessage());
            }
            
            // For full list of available annotations, see http://g.co/cloud/vision/docs
            predicted = res.getTextAnnotationsList().get(0).getDescription();
//            res.getTextAnnotationsList().stream().map(text -> predicted.append(text.getDescription()));
        }
        
        return predicted;
    }
    
}
