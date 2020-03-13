package com.cvr.it.coderunner.controller;

import java.io.IOException;

import com.cvr.it.coderunner.exception.TerminalException;
import com.cvr.it.coderunner.model.CodeRequest;
import com.cvr.it.coderunner.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PostMapping("/compile")
    @ResponseBody
    public ResponseEntity<String> compileCode(@RequestBody CodeRequest code)
    throws IOException, InterruptedException {
        try {
            return ResponseEntity.ok().body(service.compileLocally(code.getCode(), code.getLanguage(), code.getName()));
        } catch (TerminalException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
    @PostMapping("/run")
    @ResponseBody
    public ResponseEntity<String> runCode(@RequestBody CodeRequest code)
    throws IOException, InterruptedException {
        
        try {
            return ResponseEntity.ok().body(service.runLocally(code.getName()));
        } catch (TerminalException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
