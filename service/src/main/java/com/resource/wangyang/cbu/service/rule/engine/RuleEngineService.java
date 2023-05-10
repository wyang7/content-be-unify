package com.resource.wangyang.cbu.service.rule.engine;


import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import com.resource.wangyang.cbu.service.rule.dto.EngineOutput;

import java.util.List;

/**
 * @author wyang7
 */
public interface RuleEngineService {

    /**
     * 规则执行
     *
     * @param ruleContext
     * @return
     */
    EngineOutput execute(RuleContext ruleContext);
}
