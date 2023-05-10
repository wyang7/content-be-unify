package com.resource.wangyang.cbu.mq.consumer.binlog;


import com.alibaba.fastjson.JSONObject;
import com.resource.wangyang.cbu.dto.ImportSourceDTO;
import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import com.resource.wangyang.cbu.service.resource.handler.impl.ResourcePoolBinlogChangedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class resourceBinlogConsumer implements RocketMQListener<String> {

    @Resource
    private ResourcePoolBinlogChangedService resourcePoolBinlogChangedService;

    @Override
    public void onMessage(String message) {
        log.info("资源变更binlog消息, message: {}", message);
        //todo 前置参数校验


        //协议转换
        SubscribeEvent.OneRowChange changedRow= JSONObject.parseObject(message, SubscribeEvent.OneRowChange.class);
        //执行责任链处理
        resourcePoolBinlogChangedService.execute(changedRow);


    }

}
