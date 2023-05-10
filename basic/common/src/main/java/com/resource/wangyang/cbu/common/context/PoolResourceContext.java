package com.resource.wangyang.cbu.common.context;

import com.alibaba.fastjson.JSONObject;
import com.resource.wangyang.cbu.constant.TagSourceEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class PoolResourceContext {

    /**
     * 业务线code
     */
    private String business;

    /**
     * 内容池code
     */
    private String poolCode;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 资源类型
     */
    private String resourceType;


    /**
     * 创建者
     */
    private String author;

    /**
     * 资源内容, json
     */
    private JSONObject resourceContent;


    /**
     * 资源其他附属字段, json
     */
    private JSONObject extInfo;

    /**
     * 规则打标信息
     * {@link TagSourceEnum}
     */
    private Map<String, List<String>> ruleTag;
    /**
     * 运营打标信息, json， 标签渠道->渠道下的标签 id 列表
     * {@link TagSourceEnum}
     */
    private Map<String, List<String>> auditTag;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 额外状态 , json
     */
    private Map<String, String> extStatus;

    /**
     * 资源创建时间
     */
    private Date resourceCreateTime;

    /**
     * 资源发布时间
     */
    private Date resourcePublishTime;
}
