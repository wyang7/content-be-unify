package com.resource.wangyang.cbu.service.rule.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wangyang
 */
@Data
public class EngineOutput implements Serializable {
    private static final long serialVersionUID = 2904971997503847559L;

    /**
     * 场景名
     */
    private String sceneName;
    /**
     * 出参结果
     */
    private Map<String, List<String>> outputTags;


}
