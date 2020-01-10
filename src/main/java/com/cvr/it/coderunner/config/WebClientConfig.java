package com.cvr.it.coderunner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }
    
}
