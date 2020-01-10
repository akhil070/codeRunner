package com.cvr.it.coderunner.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.function.Predicate;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.BadDocumentException;
import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.utils.IoUtils;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Service
@Slf4j
public class TextractService {
    
    public String processImage(MultipartFile file) {
        
        ByteBuffer imageData;
        try (InputStream inputStream = file.getInputStream()) {
            imageData = ByteBuffer.wrap(IoUtils.toByteArray(inputStream));
            AmazonTextract client = AmazonTextractClientBuilder.defaultClient();
            
            DetectDocumentTextRequest request = new DetectDocumentTextRequest()
                    .withDocument(new Document().withBytes(imageData));
            
            DetectDocumentTextResult result = client.detectDocumentText(request);
            
            StringBuilder predictedCode = new StringBuilder();
            
            result.getBlocks().stream().filter(isTextBlock()).map(Block::getText)
                  .forEach(line -> predictedCode.append(line));
            
            return result.toString();
            
        } catch (FileNotFoundException fnfe) {
            log.error("file not found exception {}", fnfe.getStackTrace());
        } catch (IOException ie) {
            log.error("io exception {}", ie.getStackTrace());
        }
        
        throw new BadDocumentException("Failed to process document");
    }
    
    private static Predicate<Block> isTextBlock() {
        return p -> p.getBlockType().equals("WORD") || p.getBlockType().equals("LINE");
    }
}
