package com.cvr.it.coderunner.util;

import java.io.IOException;

import com.cvr.it.coderunner.exception.TerminalException;
import com.cvr.it.coderunner.model.CodeRequest;

/**
 * @author krishnamohan
 *  18/04/20
 **/

@FunctionalInterface
public interface TerminalAction {
    
    public String execute(CodeRequest codeRequest) throws InterruptedException, TerminalException, IOException;
    
}
