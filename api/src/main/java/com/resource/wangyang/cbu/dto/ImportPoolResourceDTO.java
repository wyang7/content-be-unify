package com.resource.wangyang.cbu.dto;

import com.alibaba.fastjson.JSONObject;
import com.resource.wangyang.cbu.constant.TagSourceEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 内容池内资源 DTO
 */
@Data
public class ImportPoolResourceDTO implements Serializable {

    private static final long serialVersionUID = -1159159047154363539L;
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
     * 资源创建时间
     */
    private Date resourceCreateTime;

    /**
     * 资源发布时间
     */
    private Date resourcePublishTime;

}
