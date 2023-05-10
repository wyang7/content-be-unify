package com.resource.wangyang.cbu.dao.video.cache;

import com.resource.wangyang.cbu.dao.video.domain.PoolResourceDO;
import com.resource.wangyang.cbu.dao.video.sql.impl.AbstractPoolResourceDao;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public  class PoolResourceCache extends AbstractPoolResourceDao {

    private static final MemcacheService.KeyGenerator<Long> resIDKeyGenerator = MemcacheService.newKeyGenerator(PoolResourceCache.class.getName()+"-ID");
    @Resource
    private MemcacheService memcacheService;

    public int add(PoolResourceDO data) {
        memcacheService.removeCache(data.getId(),resIDKeyGenerator);
        return super.add(data);

    }

    @Override
    public boolean update(PoolResourceDO data) {
        memcacheService.removeCache(data.getId(),resIDKeyGenerator);
        return super.update(data);
    }

    @Override
    public  PoolResourceDO getById(Long id) {
        return memcacheService.getById(id, ()->super.getById(id),
                resIDKeyGenerator, -1, DateUtils.MILLIS_PER_MINUTE, DateUtils.MILLIS_PER_MINUTE * 5);

    }




}
