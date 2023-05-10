package com.resource.wangyang.cbu.common;


import lombok.Data;
import java.io.Serializable;

/**
 * CIO 流量上下文
 *
 * @author wangyang
 */
@Data
public  class CBUContext implements Serializable {
    private static final long serialVersionUID = 2704060486901586267L;

    public static  String cbuResourceType ;
    public static  String cbuBusiness ;
    public static  String cbuPool ;


}
