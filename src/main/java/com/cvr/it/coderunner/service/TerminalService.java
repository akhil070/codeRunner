package com.cvr.it.coderunner.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
    
    private String runCommand(ArrayList<String> commands) throws IOException, InterruptedException {
        
        StringBuilder output = new StringBuilder();
        
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);
        
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
        } else {
            log.error("failed");
        }
        
        return output.toString();
        
    }
    
    public String compile(String tempFilePath, Language language) throws IOException, InterruptedException {
        
        StringBuilder output = new StringBuilder();
        
        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("gcc", tempFilePath.concat(".c"), "-o", tempFilePath));
        
        log.info("started processing the command : {} {} {} {}", "gcc", tempFilePath.concat(".c"), "-o", tempFilePath);
        
        return runCommand(commands);
    }
    
    public String run(String tempFilePath) throws IOException, InterruptedException {
        
        StringBuilder output = new StringBuilder();
        
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(tempFilePath));
        
        log.info("started processing the command : {} {} {} {}", "gcc", tempFilePath, "-o",
                 tempFilePath.substring(tempFilePath.lastIndexOf('/') + 1));
        
        return runCommand(commands);
    }
    
}
