package com.resource.wangyang.cbu.service.resource.handler.common;


import com.resource.wangyang.cbu.service.resource.event.SubscribeEvent;

public interface Handler {


    void setPre(Handler handler);

    void setNext(Handler handler);

    void execute(SubscribeEvent.OneRowChange changedRow);

    String name();
}