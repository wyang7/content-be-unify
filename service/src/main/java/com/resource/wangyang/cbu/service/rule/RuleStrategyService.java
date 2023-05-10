package com.resource.wangyang.cbu.service.rule;

import com.resource.wangyang.cbu.service.rule.core.RuleCenter;
import com.resource.wangyang.cbu.service.rule.context.RuleContext;
import com.resource.wangyang.cbu.service.rule.core.RuleInputProcessor;
import com.resource.wangyang.cbu.service.rule.dto.EngineOutput;
import com.resource.wangyang.cbu.service.rule.dto.RuleRequestDto;
import com.resource.wangyang.cbu.service.rule.dto.RuleResponseDto;
import com.resource.wangyang.cbu.service.rule.engine.RuleEngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class RuleStrategyService {

    @Resource
    private RuleCenter ruleCenter;
    @Resource
    private RuleInputProcessor ruleInputProcessor;

    @Resource
    private RuleEngineService ruleEngineService;

    public RuleResponseDto ruleCheck(RuleRequestDto ruleRequestDto){

        RuleResponseDto ruleResponseDto=new RuleResponseDto();

        //前置参数校验


        //规则初始化
        RuleContext ruleContext= ruleCenter.create(ruleRequestDto.getSceneKey());
        //  数据补全
        ruleInputProcessor.process(ruleContext);
        //规则执行
        EngineOutput engineOutput=ruleEngineService.execute(ruleContext);
        ruleResponseDto.setFeatures(ruleContext.getFeaturesInputs());
        ruleResponseDto.setParams(ruleContext.getParams());
        ruleResponseDto.setNameListInputs(ruleContext.getNameListInputs());
        ruleResponseDto.setEngineOutput(engineOutput);
        return ruleResponseDto;
    }

}
