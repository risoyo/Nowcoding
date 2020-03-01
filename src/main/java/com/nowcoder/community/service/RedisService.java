package com.nowcoder.community.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        //更改在redis里面查看key编码问题
        RedisSerializer<?> redisSerializer = new StringRedisSerializer();
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(redisSerializer);
        vo.set(key, value);
    }

    public Object get(String key) {
        RedisSerializer<?> redisSerializer = new StringRedisSerializer();// 在涉及key的读取/写入操作时，一定要设定key的序列化方法，否则会读取不到
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(redisSerializer);
        return vo.get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
