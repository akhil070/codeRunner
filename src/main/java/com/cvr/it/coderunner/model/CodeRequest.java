package com.cvr.it.coderunner.model;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/**
 * @author krishnamohan
 * @date 11/01/20
 **/

@Data
public class CodeRequest {
    
    String code;
    
    String language;
    
    String name;
    
}
