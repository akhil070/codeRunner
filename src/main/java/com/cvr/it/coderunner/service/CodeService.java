package com.cvr.it.coderunner.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.cvr.it.coderunner.config.HackerEarthConfig;
import com.cvr.it.coderunner.exception.TerminalException;
import com.cvr.it.coderunner.model.CodeRequest;
import com.cvr.it.coderunner.model.Language;
import com.cvr.it.coderunner.util.ChooseAction;
import com.cvr.it.coderunner.util.TerminalAction;
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
    ChooseAction chooseAction;
    
    public String runCommand(CodeRequest codeRequest) throws InterruptedException, TerminalException, IOException {
        return chooseAction.get(codeRequest.getCommand()).execute(codeRequest);
    }

}
