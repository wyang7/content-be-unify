package com.resource.wangyang.cbu.api;

import com.resource.wangyang.cbu.common.RpcResult;
import com.resource.wangyang.cbu.constant.TagSourceEnum;
import com.resource.wangyang.cbu.dto.ImportSourceDTO;

import java.util.List;
import java.util.Map;

public interface SourceRuleService {

    /**
     * 检测规则并且入库
     * @param importSourceDTO
     * @return 规则标签 {@link  TagSourceEnum}
     */
    RpcResult<Map<String, List<String>>> checkRuleAndImport(ImportSourceDTO importSourceDTO);

    /**
     * 检测规则不入库
     * @param importSourceDTO
     * @return 规则标签 {@link  TagSourceEnum}
     */
    RpcResult<Map<String, List<String>>> checkRule(ImportSourceDTO importSourceDTO);

}
