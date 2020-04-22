package com.github.bjlhx15.core.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private Object content;
    private String message;
    private Map<String, String> headers = new HashMap<>();

    public void setHeaders(Map<String, String> headers) {
        if(headers != null) {
            headers.forEach((key, value) -> {
                this.headers.put(key, value);
            });
        }
    }

    public void setHeader(String key, String value){
        this.headers.put(key,value);
    }
}
