package com.resource.wangyang.cbu.service.rule.engine;


import com.resource.wangyang.cbu.service.rule.context.RuleContext;

import java.util.List;
import java.util.Map;

/**
 * @author wyang7
 */
public interface RuleExecutor {

    /**
     * 规则执行
     *
     * @param expression
     * @param params
     * @return
     */
    Object execute(String expression, Map<String, Object> params);


}
