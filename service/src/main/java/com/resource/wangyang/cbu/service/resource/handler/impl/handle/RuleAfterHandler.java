package com.resource.wangyang.cbu.service.resource.handler.impl.handle;

import com.alibaba.fastjson.JSON;
import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import com.resource.wangyang.cbu.service.resource.handler.common.AbstractBinLogChangedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RuleAfterHandler extends AbstractBinLogChangedService {

    @Override
    public String name() {
        return "后置规则处理器";
    }


    @Override
    protected void invoke(SubscribeEvent.OneRowChange changedRow) {
        try {
            dealRuleExpression(changedRow);
        } catch (Exception e) {
            log.error("计算函数计算处理失败, event：{}", JSON.toJSON(changedRow), e);
        }
    }

    private void dealRuleExpression(SubscribeEvent.OneRowChange row) {
        if (row.getType() == SubscribeEvent.RowChangeType.DELETE) {
            return;
        }
        //todo 此处写清楚具体的变更类型对应的规则调用即可

    }


    @Override
    public int getOrder() {
        return 1;
    }
}
