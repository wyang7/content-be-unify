package com.resource.wangyang.cbu.service.rule.core.processer;


import com.resource.wangyang.cbu.service.rule.context.RuleContext;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wyang7
 */
public interface InputProcessor {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100,
            1, TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(10000), new ThreadPoolExecutor.CallerRunsPolicy());

    Map<String, Object> process(RuleContext ruleContext);
}
