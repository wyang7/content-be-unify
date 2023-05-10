package com.resource.wangyang.cbu.dao.video.sql.impl;

import com.resource.wangyang.cbu.dao.video.domain.PoolResourceDO;
import com.resource.wangyang.cbu.dao.video.sql.PoolResourceDao;

import javax.annotation.Resource;

public abstract class AbstractPoolResourceDao implements PoolResourceDao {



    @Override
    public int add(PoolResourceDO data) {

        // db insert
        return 0;
    }

    @Override
    public boolean update(PoolResourceDO data) {

        // db update
        return false;
    }

    @Override
    public PoolResourceDO getById(Long id) {
        // db sql getId操作
        return null;
    }


}
