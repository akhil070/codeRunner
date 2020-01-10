package com.cvr.it.coderunner.controller;

import com.cvr.it.coderunner.service.CodeService;
import org.json.JSONObject;
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
    public JSONObject compileCode(@RequestBody String code, @PathVariable("version") String version) {
        if (version.equals("v2")) {
            return service.compileCode(code);
        } else return null;
    }
    
    @PostMapping("{version:v1}/run")
    @ResponseBody
    public JSONObject runCode(@RequestBody String code, @PathVariable("version") String version) {
        if (version.equals("v2")) {
            return service.runCode(code);
        } else return null;
    }
    
}
