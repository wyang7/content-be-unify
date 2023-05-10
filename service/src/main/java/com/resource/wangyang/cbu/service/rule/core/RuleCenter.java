package com.resource.wangyang.cbu.service.rule.core;

import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import org.springframework.stereotype.Component;

@Component
public class RuleCenter {

    public RuleContext create(String sceneKey){
        //todo 从db侧获取规则
        return new RuleContext();
    }
}
