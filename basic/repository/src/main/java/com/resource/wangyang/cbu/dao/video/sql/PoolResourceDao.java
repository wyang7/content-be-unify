package com.resource.wangyang.cbu.dao.video.sql;



import com.resource.wangyang.cbu.dao.video.domain.PoolResourceDO;

import java.util.List;

/**
 *
 * @author wangyang
 */
public interface PoolResourceDao {


    /**
     * 添加资源
     *
     * @param data 资源
     * @return
     */
    int add(PoolResourceDO data);

    /**
     * 更新资源
     *
     * @param data 资源
     * @return
     */
    boolean update(PoolResourceDO data);

    /**
     * 批量 id 获取资源
     *
     * @param id 主键 id
     * @return
     */
    public PoolResourceDO getById(Long id);



}
