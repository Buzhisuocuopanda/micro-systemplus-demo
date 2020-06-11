package com.mkst.mini.systemplus.common.shiroredis.serializer;

import com.mkst.mini.systemplus.common.shiroredis.exception.SerializationException;

public interface RedisSerializer<T> {

    byte[] serialize(T t) throws SerializationException;

    T deserialize(byte[] bytes) throws SerializationException;
}
