package com.resource.wangyang.cbu.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.resource.wangyang.cbu.common.context.PoolResourceContext;
import com.resource.wangyang.cbu.common.utils.BeanConverterUtils;
import com.resource.wangyang.cbu.dao.video.cache.PoolResourceCache;
import com.resource.wangyang.cbu.dao.video.domain.PoolResourceDO;
import com.resource.wangyang.cbu.dto.ImportSourceDTO;
import com.resource.wangyang.cbu.service.adapter.RuleStrategyAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class ImportSourceConsumer implements RocketMQListener<String> {

    @Resource
    private RuleStrategyAdapter ruleStrategyAdapter;

    @Resource
    private PoolResourceCache poolResourceCache;

    @Override
    public void onMessage(String message) {
        log.info("资源入库消息, message: {}", message);
        ImportSourceDTO importSourceDTO = JSONObject.parseObject(message, ImportSourceDTO.class);

        //todo 前置消息合法性判定&参数校验


        //内部流转上下文封装
        PoolResourceContext poolResourceContext= BeanConverterUtils.convertToPoolResourceContext(importSourceDTO);
        //  todo 入库类型判定 insert&update&upsert&partUpsert

        //前置规则判定----以上都需抽出来
        Map<String, List<String>> ruleTag= ruleStrategyAdapter.getTagFromRule(poolResourceContext);
        //规则标注入表DTO
        poolResourceContext.setRuleTag(ruleTag);
        //资源子池策略判定，打子池标
        String poolCode=ruleStrategyAdapter.getPoolCodeFromRule(poolResourceContext);
        poolResourceContext.setPoolCode(poolCode);
        //入库---缓存
        PoolResourceDO poolResourceDO=convertToPoolResourceDO(poolResourceContext);
        poolResourceCache.add(poolResourceDO);

    }
    private PoolResourceDO convertToPoolResourceDO(PoolResourceContext poolResourceContext){
        //todo Bean转换
        return new PoolResourceDO();
    }
}
