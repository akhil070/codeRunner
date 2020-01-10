package com.cvr.it.coderunner.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import lombok.Getter;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Configuration
@Getter
public class HackerEarthConfig {
    private String url;
    private String scheme;
    private Integer port;
    private String compilePath;
    private String runPath;
    private String clientSecret;
    private Map<String, String> defaults;
}
