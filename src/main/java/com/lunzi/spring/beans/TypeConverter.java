package com.lunzi.spring.beans;

/**
 * 类型转化接口
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value,Class<T> requireType);
}
