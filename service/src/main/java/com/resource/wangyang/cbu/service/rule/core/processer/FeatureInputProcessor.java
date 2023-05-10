package com.resource.wangyang.cbu.service.rule.core.processer;


import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @author wyang7
 */
@Service
@Slf4j
public class FeatureInputProcessor implements InputProcessor {


    @Override
    public Map<String, Object> process(RuleContext ruleContext) {
        //todo 指标补全
        return null;
    }
}
