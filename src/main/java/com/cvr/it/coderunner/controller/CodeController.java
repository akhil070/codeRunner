package com.cvr.it.coderunner.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.cvr.it.coderunner.exception.TerminalException;
import com.cvr.it.coderunner.model.CodeRequest;
import com.cvr.it.coderunner.model.Language;
import com.cvr.it.coderunner.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Controller
@RequestMapping("/code")
@Slf4j
public class CodeController {
    
    @Autowired
    private CodeService service;
    
    @CrossOrigin
    @PostMapping("/compile")
    @ResponseBody
    public ResponseEntity<String> compileCode(@RequestBody CodeRequest code)
    throws IOException, InterruptedException {
        try {
            code.setCommand("compile");
            String response = service.runCommand(code);
            
            if (Language.valueOf("JS").equals(code.getLanguage())) {
                code.setCommand("run");
                return ResponseEntity.ok().body(service.runCommand(code));
            }
            
            return ResponseEntity.ok().body("success");
            
        } catch (TerminalException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
    @CrossOrigin
    @PostMapping("/run")
    @ResponseBody
    public ResponseEntity<String> runCode(@RequestBody CodeRequest code)
    throws IOException, InterruptedException, TerminalException {
        
        try {
            code.setCommand("run");
            return ResponseEntity.ok().body(service.runCommand(code));
        } catch (TerminalException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (FileNotFoundException fe) {
            if (Language.valueOf("JS").equals(code.getLanguage())) {
                try {
                    code.setCommand("compile");
                    String response = service.runCommand(code);
                    code.setCommand("run");
                    response = service.runCommand(code);
                    return ResponseEntity.ok().body(response);
                } catch (TerminalException te2) {
                    return ResponseEntity.badRequest().body(te2.getMessage());
                }
            }
            return ResponseEntity.badRequest().body(fe.getMessage());
        }
    }
    
}
