package com.resource.wangyang.cbu.service.rule.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangyang
 */
public class RuleRequestDto implements Serializable {

    private static final long serialVersionUID = 1679126174189219392L;

    private String sceneKey;

    private Map<String, Object> params;

    public RuleRequestDto(String sceneKey, Map<String, Object> params) {
        this.sceneKey = sceneKey;
        this.params = params;
    }

    public String getSceneKey() {
        return sceneKey;
    }

    public RuleRequestDto setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
        return this;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public RuleRequestDto setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }
}
