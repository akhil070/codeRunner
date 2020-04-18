package com.cvr.it.coderunner.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cvr.it.coderunner.exception.TerminalException;
import com.cvr.it.coderunner.model.Language;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 11/01/20
 **/

@Service
@Slf4j
public class TerminalService {
    
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";
    
    public String runCommand(List<String> commands)
    throws IOException, InterruptedException, TerminalException {
        
        StringBuilder output = new StringBuilder();
        
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);
        processBuilder.redirectErrorStream(true);
    
        log.info("started processing the command : {}", commands);
        
        Process process = processBuilder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }
        
        process.waitFor();
        
        int exited = process.exitValue();
        if (exited == 0) {
            log.info("success");
            return output.toString();
        } else {
            log.error("failed");
            throw new TerminalException(output.toString());
        }
        
    }
    
    public String compile(String tempFilePath, Language language)
    throws IOException, InterruptedException, TerminalException {
        
        StringBuilder output = new StringBuilder();
        
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("gcc", tempFilePath.concat(".c"), "-o", tempFilePath));
        
        log.info("started processing the command : {} {} {} {}", "gcc", tempFilePath.concat(".c"), "-o", tempFilePath);
        
        return runCommand(commands);
    }
    
    public String run(String tempFilePath, Language language) throws IOException, InterruptedException, TerminalException {
        
        ArrayList<String> commands = null;
        if (Language.valueOf("C").equals(language)) {
            commands = new ArrayList<>(Arrays.asList("./" + tempFilePath));
        } else {
            commands = new ArrayList<>(Arrays.asList("node", tempFilePath + ".js"));
        }
        
        File f = new File(tempFilePath);
        if (!f.exists() && Language.valueOf("C").equals(language)) {
            throw new FileNotFoundException("compile code before executing");
        }
        
        log.info("started processing the command : ", tempFilePath);
        
        return runCommand(commands);
    }
    
}
