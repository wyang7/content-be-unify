package com.resource.wangyang.cbu.service.rule.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangyang
 */
public class RuleResponseDto implements Serializable {
    private static final long serialVersionUID = -5179467161901827672L;
    /**
     * 请求参数
     */
    private Map<String, Object> params;
    /**
     * 指标数据
     */
    private Map<String, Object> features;
    /**
     * 名单数据
     */
    private Map<String, Object> nameListInputs;
    /**
     * 引擎结果
     */
    private EngineOutput engineOutput;

    public Map<String, Object> getParams() {
        return params;
    }

    public RuleResponseDto setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }

    public RuleResponseDto setFeatures(Map<String, Object> features) {
        this.features = features;
        return this;
    }

    public EngineOutput getEngineOutput() {
        return engineOutput;
    }

    public RuleResponseDto setEngineOutput(EngineOutput engineOutput) {
        this.engineOutput = engineOutput;
        return this;
    }

    public Map<String, Object> getNameListInputs() {
        return nameListInputs;
    }

    public RuleResponseDto setNameListInputs(Map<String, Object> nameListInputs) {
        this.nameListInputs = nameListInputs;
        return this;
    }

}
