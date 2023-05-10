package com.resource.wangyang.cbu.dao.video.cache;

import com.resource.wangyang.cbu.common.utils.MD5;
import com.whalin.MemCached.MemCachedClient;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Supplier;

public class MemcacheService {

    private MemCachedClient memCachedClient;

    public static <ID> MemcacheService.KeyGenerator<ID> newKeyGenerator(String self) {
        return (id) -> {
            return buildKey(self == null ? "-null-" : self, String.valueOf(id));
        };
    }

    public static String buildKey(String... items) {
        StringJoiner joiner = new StringJoiner("_");
        String[] var2 = items;
        int var3 = items.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String item = var2[var4];
            joiner.add(item);
        }

        return MD5.md5String(joiner.toString());
    }

    public void remove(String key) {
        this.removeCache(key);
    }

    public <ID> boolean removeCache(ID id, MemcacheService.KeyGenerator<ID> generator) {
        return this.removeCache(generator.buildIdKey(id));
    }

    public boolean removeCache(String key) {
        return this.memCachedClient.delete(key);
    }


    public <ID, T> T getById(ID id, Supplier<T> run, MemcacheService.KeyGenerator<ID> gen, long cacheTime, long holeTime, long expire) {
        return this.fromCacheWithExpire(gen.buildIdKey(id), run, cacheTime, (obj) -> obj == null ? holeTime : expire);
    }

    public <T> T fromCacheWithExpire(String key, Supplier<T> run, long cacheTime, Function<T, Long> expire) {
        return this.fromCacheWithExpire(key, null, run, cacheTime, expire);
    }

    public <T> T fromCacheWithExpire(String key, T first, Supplier<T> run, long cacheTime, Function<T, Long> expire) {
        MemcacheService.HoleWrapper<T> obj = (MemcacheService.HoleWrapper)this.memCachedClient.get(key);
        MemcacheService.HoleWrapper newObj;
        if (obj != null) {
            if (obj.getExpire() >= System.currentTimeMillis()) {
                return obj.getTarget();
            }

            newObj = new MemcacheService.HoleWrapper(this.calculateExpire(expire.apply(obj.getTarget())), obj.getTarget());
            this.cachedObject(key, newObj, cacheTime);
        } else if (first != null) {
            newObj = new MemcacheService.HoleWrapper(this.calculateExpire(expire.apply(first)), first);
            this.cachedObject(key, newObj, cacheTime);
        }

        T value = run.get();
        MemcacheService.HoleWrapper<T> newObjCache = new MemcacheService.HoleWrapper(this.calculateExpire((Long)expire.apply(value)), value);
        this.cachedObject(key, newObjCache, cacheTime);
        return value;
    }

    public <T> boolean cachedObject(String key, T obj, long cacheTime) {
        if (obj == null) {
            return false;
        } else {
            return cacheTime > 0L ? this.memCachedClient.set(key, obj, new Date(cacheTime)) : this.memCachedClient.set(key, obj);
        }
    }

    private long calculateExpire(long timeout) {
        return timeout > 0L ? System.currentTimeMillis() + timeout : 9223372036854775807L;
    }

    public static class HoleWrapper<T> implements Serializable {
        private static final long serialVersionUID = 5561603423218156697L;
        private long expire;
        private T target;

        public HoleWrapper(long expire, T target) {
            this.expire = expire;
            this.target = target;
        }

        public HoleWrapper() {
        }

        public long getExpire() {
            return this.expire;
        }

        public T getTarget() {
            return this.target;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public void setTarget(T target) {
            this.target = target;
        }
    }

    @FunctionalInterface
    public interface KeyGenerator<ID> {
        String buildIdKey(ID var1);

        default String buildKey(String... items) {
            StringJoiner joiner = new StringJoiner("_");
            joiner.add(this.getClass().getName());
            String[] var3 = items;
            int var4 = items.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String item = var3[var5];
                joiner.add(item);
            }

            return MD5.md5String(joiner.toString());
        }
    }
}
