package com.hubenas.kryo.util;

import com.janty.core.util.KryoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class CustomSerializationRedisSerializer
  implements RedisSerializer<Object>
{
  private Converter<Object, byte[]> serializer = new SerializingConverter();
  private Converter<byte[], Object> deserializer = new DeserializingConverter();
  private static final byte[] EMPTY_ARRAY = new byte[0];
  private static final Logger logger = LoggerFactory.getLogger(CustomSerializationRedisSerializer.class);
  
  private static boolean isEmpty(byte[] data)
  {
    return (data == null) || (data.length == 0);
  }
  
  @Override
  public Object deserialize(byte[] bytes)
  {
    if (isEmpty(bytes)) {
      return null;
    }
    try
    {
      return KryoUtils.deserialize(bytes);
    }
    catch (Exception e)
    {
      logger.error("deserialize error {}", e.getMessage(), e);
      try
      {
        return this.deserializer.convert(bytes);
      }
      catch (Exception ex)
      {
        throw new SerializationException("Cannot deserialize", ex);
      }
    }
  }
  
  @Override
  public byte[] serialize(Object object)
  {
    if (object == null) {
      return EMPTY_ARRAY;
    }
    try
    {
      return KryoUtils.serialize(object);
    }
    catch (Exception e)
    {
      logger.error("serialize error {}", e.getMessage(), e);
      try
      {
        return (byte[])this.serializer.convert(object);
      }
      catch (Exception ex)
      {
        throw new SerializationException("Cannot serialize", ex);
      }
    }
  }
}
