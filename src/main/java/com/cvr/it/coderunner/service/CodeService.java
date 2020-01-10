package com.cvr.it.coderunner.service;

import com.cvr.it.coderunner.config.HackerEarthConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Service
@Slf4j
public class CodeService {
    
    @Autowired
    HackerEarthService hackerEarthService;
    
    @Autowired
    HackerEarthConfig config;
    
    public JSONObject compileCode(String code) {
        return hackerEarthService.callHackerEarth(code, config.getCompilePath());
    }
    
    public JSONObject runCode(String code) {
        return hackerEarthService.callHackerEarth(code, config.getRunPath());
    }

}
