package com.resource.wangyang.cbu.service.resource.handler.common;


import com.alibaba.fastjson.JSON;
import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

import java.util.Objects;

/**
 * 模版
 */
@Slf4j
public abstract class AbstractBinLogChangedService implements Handler, Ordered {

    public Handler next = null;

    public Handler pre = null;

    @Override
    public void setPre(Handler handler) {
        this.pre = handler;
    }

    @Override
    public void setNext(Handler handler) {
        if (Objects.isNull(this.next)) {
            this.next = handler;
            handler.setPre(this);
        } else {
            this.next.setNext(handler);
        }
    }

    /**
     * @param changedRow
     */
    @Override
    public void execute(SubscribeEvent.OneRowChange changedRow) {
        try {
            if (Objects.nonNull(changedRow)) {
                invoke(changedRow);
            }
            if (Objects.nonNull(next)) {
                next.execute(changedRow);
            }
        } catch (Exception e) {
            log.error("ndc handler invoke error, changedRow:{}", JSON.toJSON(changedRow), e);
        }
    }

    /**
     * 执行逻辑
     *
     * @param changedRow
     * @return
     */
    protected abstract void invoke(SubscribeEvent.OneRowChange changedRow) throws Exception;


}
