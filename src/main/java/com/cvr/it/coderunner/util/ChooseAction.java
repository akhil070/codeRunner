package com.cvr.it.coderunner.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.cvr.it.coderunner.model.Language;
import com.cvr.it.coderunner.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 18/04/20
 **/

@Service
@Slf4j
public class ChooseAction {
    
    @Autowired
    public TerminalService terminalService;
    
    public TerminalAction get(String command) {
        if (command.equals("run")) {
            return (codeRequest -> {
                return terminalService.run(codeRequest.getName(), codeRequest.getLanguage());
            });
        } else {
            return  (codeRequest -> {
    
    
                try {
        
                    FileOutputStream file = null;
        
                    if (Language.valueOf("JS").equals(codeRequest.getLanguage())) {
                        file = new FileOutputStream(codeRequest.getName() + ".js");
                    } else {
                        file = new FileOutputStream(codeRequest.getName() + ".c");
                    }
        
                    file.write(codeRequest.getCode().getBytes());
                    file.close();
                } catch (IOException ex) {
                    log.error("failed to created file {}", ex.getStackTrace());
                    throw new IOException("failed to create file for compilation " + codeRequest.getName());
                }
    
                if (Language.valueOf("C").equals(codeRequest.getLanguage())) {
                    return terminalService.compile(codeRequest.getName(), codeRequest.getLanguage());
                } else {
                    return "success";
                }
                
            });
        }
    }
    
}
