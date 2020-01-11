package com.cvr.it.coderunner.controller;

import java.io.IOException;

import com.cvr.it.coderunner.model.CodeRequest;
import com.cvr.it.coderunner.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    CodeService service;
    
    @PostMapping("{version:v1}/compile")
    @ResponseBody
    public String compileCode(@RequestBody CodeRequest code, @PathVariable("version") String version)
    throws IOException, InterruptedException {
        if (version.equals("v2")) {
            return service.compileCode(code.getCode()).toString();
        } else {
            return service.compileLocally(code.getCode(), code.getLanguage(), code.getName());
        }
    }
    
    @PostMapping("{version:v1}/run")
    @ResponseBody
    public String runCode(@RequestBody CodeRequest code, @PathVariable("version") String version)
    throws IOException, InterruptedException {
        if (version.equals("v2")) {
            return service.runCode(code.getCode()).toString();
        } else {
            return service.runLocally(code.getName());
        }
    }
    
}
