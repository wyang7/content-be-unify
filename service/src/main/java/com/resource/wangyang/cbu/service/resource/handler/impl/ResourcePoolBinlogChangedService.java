package com.resource.wangyang.cbu.service.resource.handler.impl;


import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;
import com.resource.wangyang.cbu.service.resource.handler.common.AbstractBinLogChangedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * binLog处理服务
 */
@Slf4j
@Component
public class ResourcePoolBinlogChangedService implements ApplicationContextAware {

    @Autowired
    private AbstractBinLogChangedService defaultChain;

    private List<AbstractBinLogChangedService> abstractRelatedResourceServices;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,AbstractBinLogChangedService> baseDataParseMap= applicationContext.getBeansOfType(AbstractBinLogChangedService.class);

        baseDataParseMap.forEach((k,v)->{
            abstractRelatedResourceServices.add(v);
        });
        //按order决定链式顺序
        abstractRelatedResourceServices.stream()
                .filter(c -> !Objects.equals(c, defaultChain))
                .sorted(Comparator.comparing(AbstractBinLogChangedService::getOrder, Integer::compareTo))
                .forEach(defaultChain::setNext);
    }


    /**
     * 同步获取
     *
     * @param changedRow        变更数据行
     * @return
     */
    public void execute(SubscribeEvent.OneRowChange changedRow) {
        defaultChain.execute(changedRow);
    }

}
