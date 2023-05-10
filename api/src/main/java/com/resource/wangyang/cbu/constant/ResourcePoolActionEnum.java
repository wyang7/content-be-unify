package com.resource.wangyang.cbu.constant;

public enum ResourcePoolActionEnum {

    ADD("add"),
    INSERT("添加"),
    PART_UPDATE("局部更新"),
    UPDATE("全量更新"),
    DELETE("delete"),
    // 如果数据库存在，有就局部更新，否则就插入
    UPSERT("更新或添加");

    private String message;

    ResourcePoolActionEnum() {
    }

    ResourcePoolActionEnum(String message) {
        this.message = message;
    }

    public String getName() {
        return name();
    }

    public String getMessage() {
        return message;
    }
}
