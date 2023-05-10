package com.resource.wangyang.cbu.service.impl;

import com.resource.wangyang.cbu.api.SourceRuleService;
import com.resource.wangyang.cbu.common.RpcResult;
import com.resource.wangyang.cbu.common.context.PoolResourceContext;
import com.resource.wangyang.cbu.common.utils.BeanConverterUtils;
import com.resource.wangyang.cbu.dto.ImportSourceDTO;
import com.resource.wangyang.cbu.service.adapter.RuleStrategyAdapter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class SourceRuleServiceImpl implements SourceRuleService {

    @Resource
    private RuleStrategyAdapter ruleStrategyAdapter;
    @Override
    public RpcResult<Map<String, List<String>>> checkRuleAndImport(ImportSourceDTO importSourceDTO) {

        //内部流转上下文封装
        PoolResourceContext poolResourceContext= BeanConverterUtils.convertToPoolResourceContext(importSourceDTO);
        //  todo 入库类型判定 insert&update&upsert&partUpsert

        //前置规则判定
        Map<String, List<String>> tags= ruleStrategyAdapter.getTagFromRule(poolResourceContext);

        //todo  后续异步流程,消息包装塞入tags，MQ后续异步处理

        //返回
        return new RpcResult<>(tags);
    }

    @Override
    public RpcResult<Map<String, List<String>>> checkRule(ImportSourceDTO importSourceDTO) {
        //todo 前置消息合法性判定&参数校验


        //内部流转上下文封装
        PoolResourceContext poolResourceContext= BeanConverterUtils.convertToPoolResourceContext(importSourceDTO);
        //  todo 入库类型判定 insert&update&upsert&partUpsert

        //前置规则判定
        return new RpcResult<>(ruleStrategyAdapter.getTagFromRule(poolResourceContext));
    }
}
