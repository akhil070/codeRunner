package com.cvr.it.coderunner.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.cvr.it.coderunner.config.HackerEarthConfig;
import com.cvr.it.coderunner.model.Language;
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
    TerminalService terminalService;
    
    @Autowired
    HackerEarthConfig config;
    
    public JSONObject compileCode(String code) {
        return hackerEarthService.callHackerEarth(code, config.getCompilePath());
    }
    
    public JSONObject runCode(String code) {
        return hackerEarthService.callHackerEarth(code, config.getRunPath());
    }
    
    public String compileLocally(String code, Language language, String fileName) throws IOException,
                                                                                         InterruptedException {
        
        try {
            FileOutputStream file = new FileOutputStream("/home/krishnamohan/Music/" + fileName + ".c");
            file.write(code.getBytes());
            file.close();
        } catch (IOException ex) {
            log.error("failed to created file {}", ex.getStackTrace());
            throw new IOException("failed to create file for compilation " + fileName);
        }
        
        return terminalService.compile("/home/krishnamohan/Music/" + fileName, language);
        
    }
    
    public String runLocally(String fileName) throws IOException, InterruptedException {
        
        return terminalService.run("/home/krishnamohan/Music/" + fileName);
        
    }

}
