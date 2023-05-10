package com.resource.wangyang.cbu.service.rule.core;


import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import com.resource.wangyang.cbu.service.rule.core.processer.FeatureInputProcessor;
import com.resource.wangyang.cbu.service.rule.core.processer.NameListInputProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wyang7
 */
@Component
public class RuleInputProcessor {
    @Resource
    private NameListInputProcessor nameListProcess;
    @Resource
    private FeatureInputProcessor featureProcess;


    /**
     * 规则上下文数据加工&补全
     *
     * @param ruleContext 规则上下文
     */
    public void process(RuleContext ruleContext) {



        ruleContext.setNameListInputs(nameListProcess.process(ruleContext));

        ruleContext.setFeaturesInputs(featureProcess.process(ruleContext));


    }

}
