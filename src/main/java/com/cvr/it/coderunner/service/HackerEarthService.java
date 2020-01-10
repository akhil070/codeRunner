package com.cvr.it.coderunner.service;

import java.net.URI;
import java.util.HashMap;

import com.cvr.it.coderunner.config.HackerEarthConfig;
import com.cvr.it.coderunner.model.HackerEarthRequest;
import com.cvr.it.coderunner.util.WebUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Service
@Slf4j
public class HackerEarthService {
    
    private static final String DEFAULT_LANGAUGE = "C";
    
    @Autowired
    HackerEarthConfig config;
    
    @Autowired
    WebClient webClient;
    
    public JSONObject callHackerEarth(String code, String call) {
        URI uri = WebUtils.buildURI(config.getUrl(), config.getScheme(), config.getPort(), call,
                                    new HashMap<>());
        HackerEarthRequest request = new HackerEarthRequest(config.getClientSecret(), config.getDefaults().get("async"),
                                                            code, config.getDefaults().get("time-limit"),
                                                            config.getDefaults().get("memory-limit"));
    
        String response = webClient.post().uri(uri).body(Mono.just(request), HackerEarthRequest.class).retrieve()
                                   .bodyToMono(String.class).block();
    
        JSONObject json = new JSONObject(response);
        
        return json;
    }
    
    
}
