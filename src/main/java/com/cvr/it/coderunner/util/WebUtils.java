package com.cvr.it.coderunner.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

import org.apache.http.client.utils.URIBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishnamohan
 * @date 10/01/20
 **/

@Slf4j
public class WebUtils {
    
    public static URI buildURI(String host, String scheme, Integer port, String path, Map<String, String> params) {
        URIBuilder builder = new URIBuilder();
        builder.setHost(host);
        builder.setPort(Objects.isNull(port) ? 80 : port);
        builder.setScheme(scheme);
        builder.setPath(path);
        if (Objects.nonNull(params)) {
            params.forEach((key, value) -> builder.setParameter(key, value));
        }
        try {
            return builder.build();
        } catch (URISyntaxException e) {
            log.error("Unable to create URI fo given params: [host:{}, scheme: {}, port: {}]", host, scheme, port);
            return null;
        }
    }
}
