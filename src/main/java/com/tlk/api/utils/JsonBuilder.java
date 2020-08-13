package com.tlk.api.utils;

import org.json.simple.JSONObject;

/**
 * JSON Strong Builder
 *
 * @author 안지호
 * @since 2020.08.13
 */
public class JsonBuilder {

    private JSONObject jsonObj;

    public JsonBuilder add(String name, Object value) {
        if(jsonObj == null) jsonObj = new JSONObject();
        jsonObj.put(name, value);
        return this;
    }

    public String build() {
        return this.jsonObj.toJSONString();
    }
}
