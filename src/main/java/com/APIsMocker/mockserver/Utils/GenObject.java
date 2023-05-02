package com.APIsMocker.mockserver.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class GenObject {
    Map<String, Object> data = new LinkedHashMap<>();


    @JsonAnySetter
    public void setData(String key, Object value) {
        data.put(key, value);
    }


    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    
    
}
