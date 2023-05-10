package com.resource.wangyang.cbu.service.adapter;

import com.resource.wangyang.cbu.common.context.PoolResourceContext;
import com.resource.wangyang.cbu.dto.ImportPoolResourceDTO;
import com.resource.wangyang.cbu.service.rule.RuleStrategyService;
import com.resource.wangyang.cbu.service.rule.dto.RuleRequestDto;
import com.resource.wangyang.cbu.service.rule.dto.RuleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 规则引擎适配器
 */
@Component
@Slf4j
public class RuleStrategyAdapter {

    private final String poolCodeRulePrefix="poolCodeRule_";
    private final String poolCodeResultKey="poolCodeResultKey";
    private final String defaultPoolCode="defaultPoolCode";

    @Resource
    private RuleStrategyService ruleStrategyService;

    public Map<String, List<String>>  getTagFromRule(PoolResourceContext poolResourceContext){

        //前置校验


        //规则调用
        RuleRequestDto ruleRequestDto=new RuleRequestDto(poolResourceContext.getBusiness(),poolResourceContext.getResourceContent().getInnerMap());
        RuleResponseDto ruleResponseDto=ruleStrategyService.ruleCheck(ruleRequestDto);
        if (null!=ruleResponseDto&&null!=ruleResponseDto.getEngineOutput()){
            return ruleResponseDto.getEngineOutput().getOutputTags();
        }

        return new HashMap<>();
    }

    public String getPoolCodeFromRule(PoolResourceContext poolResourceContext){
        //前置校验

        //装配策略入参
        Map<String, Object> params= poolResourceContext.getResourceContent().getInnerMap();
        params.putAll(poolResourceContext.getRuleTag());
        //todo 此部分协议需注意，所有规则打标的value都是List;协议层面需要作统一标准化处理
        //规则调用
        RuleRequestDto ruleRequestDto=new RuleRequestDto(poolCodeRulePrefix+poolResourceContext.getBusiness(),params);
        RuleResponseDto ruleResponseDto=ruleStrategyService.ruleCheck(ruleRequestDto);
        //结果解析
        if (null!=ruleResponseDto&&null!=ruleResponseDto.getEngineOutput()
        &&null!=ruleResponseDto.getEngineOutput().getOutputTags()){
            Map<String, List<String>> outPutTags= ruleResponseDto.getEngineOutput().getOutputTags();
            return outPutTags.getOrDefault(poolCodeResultKey, Collections.singletonList(defaultPoolCode)).get(0);
        }
        return defaultPoolCode;
    }


}
