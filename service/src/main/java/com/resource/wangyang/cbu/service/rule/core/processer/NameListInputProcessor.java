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
public class NameListInputProcessor implements InputProcessor {
    @Override
    public Map<String, Object> process(RuleContext ruleContext) {
        //todo 名单类补全
        return null;
    }
}
