package com.resource.wangyang.cbu.service.rule.context;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author  wangyang
 */
@Data
@Accessors(chain = true)
public class RuleContext {
    /**
     * 规则key
     */
    private String ruleKey;
    /**
     * 规则入参
     */
    private Map<String, Object> params;
    /**
     * 指标参数
     */
    private Map<String, Object> featuresInputs;
    /**
     * 名单参数
     */
    private Map<String, Object> nameListInputs;
    /**
     * 规则策略
     */
    private String expression;
    /**
     * 命中标
     */
    private Map<String, List<String>> hitTag;
}
