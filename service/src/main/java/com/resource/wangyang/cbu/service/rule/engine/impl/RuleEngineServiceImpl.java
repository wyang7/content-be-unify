package com.resource.wangyang.cbu.service.rule.engine.impl;


import com.google.common.collect.Maps;
import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import com.resource.wangyang.cbu.service.rule.dto.EngineOutput;
import com.resource.wangyang.cbu.service.rule.engine.RuleEngineService;
import com.resource.wangyang.cbu.service.rule.engine.mvel.MvelExecutor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wyang7
 */
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    @Resource(name = "ruleStrategyExecutor")
    private MvelExecutor ruleStrategyExecutor;


    /**
     * 策略执行
     *
     * @param ruleContext 规则上下文
     * @return 命中的策略详情
     */
    @Override
    public EngineOutput execute(RuleContext ruleContext) {
        //  前置参数检查

        //  规则环境变量
        Map<String, Object> ruleParams = new HashMap<>(ruleContext.getParams());
        ruleParams.putAll(Optional.ofNullable(ruleContext.getFeaturesInputs()).orElse(Maps.newHashMap()));
        ruleParams.putAll(Optional.ofNullable(ruleContext.getNameListInputs()).orElse(Maps.newHashMap()));

        EngineOutput engineOutput = new EngineOutput();
        engineOutput.setSceneName(ruleContext.getRuleKey());
        if (Boolean.TRUE.equals(ruleStrategyExecutor.execute(ruleContext.getExpression(), ruleContext.getParams()))) {
            engineOutput.setOutputTags(ruleContext.getHitTag());
        }
        return engineOutput;
    }
}
