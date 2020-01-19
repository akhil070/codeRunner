package com.cvr.it.coderunner.config;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @authorkrishnamohan
 * @date 11/01/20
 **/

@Configuration
public class AmazonClientConfig {
    
    @Bean
    public AmazonTextract getClient() {
        return AmazonTextractClientBuilder.defaultClient();
    }
}
