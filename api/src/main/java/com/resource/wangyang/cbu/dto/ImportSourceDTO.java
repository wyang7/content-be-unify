package com.resource.wangyang.cbu.dto;


import lombok.Data;

import java.io.Serializable;
@Data
public class ImportSourceDTO implements Serializable {
    private static final long serialVersionUID = 5934289447489590406L;

    private String importEventId;

    private String importAction;

    private long importTime;

    private Long version;

    /**
     * 或业务推送（{@link ImportPoolResourceDTO}）
     */
    private ImportPoolResourceDTO businessData;

    /**
     * 兼容 全量 HIVE 任务不好给 obj 的问题（直接给 businessData json 序列化的结果）
     */
    private String businessDataJson;

}
