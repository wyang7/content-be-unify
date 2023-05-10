package com.resource.wangyang.cbu.service.resource.handler.impl.handle;

import com.alibaba.fastjson.JSON;
import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import com.resource.wangyang.cbu.service.resource.handler.common.AbstractBinLogChangedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheHandler extends AbstractBinLogChangedService {

    @Override
    public String name() {
        return "缓存一致性处理器";
    }


    @Override
    protected void invoke(SubscribeEvent.OneRowChange changedRow) {
        try {
            //todo  缓存一致性更新，注意：为避免时序问题，需要对缓存增加version，乐观锁判定，只更新高版本
        } catch (Exception e) {
            log.error("缓存一致性处理器, event：{}", JSON.toJSON(changedRow), e);
        }
    }



    @Override
    public int getOrder() {
        return -1;
    }
}
