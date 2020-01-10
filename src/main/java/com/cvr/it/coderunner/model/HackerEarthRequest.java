package com.cvr.it.coderunner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author krishnamohan
 * @date 11/01/20
 **/

@Data
@AllArgsConstructor
public class HackerEarthRequest {
    private String clientSecret;
    private String async;
    private String source;
    private String timeLimit;
    private String memoryLimit;
}
