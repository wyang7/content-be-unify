package com.resource.wangyang.cbu.service.resource.handler.impl.handle;

import com.alibaba.fastjson.JSON;
import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import com.resource.wangyang.cbu.service.resource.handler.common.AbstractBinLogChangedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EsAfterHandler extends AbstractBinLogChangedService {

    @Override
    public String name() {
        return "ES索引写入处理器";
    }


    @Override
    protected void invoke(SubscribeEvent.OneRowChange changedRow) {
        try {
            //todo  ES 索引内容更新
        } catch (Exception e) {
            log.error("ES索引写入处理器, event：{}", JSON.toJSON(changedRow), e);
        }
    }



    @Override
    public int getOrder() {
        return 0;
    }
}
