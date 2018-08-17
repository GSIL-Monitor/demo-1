package com.lunzi.spring.beans;

/**
 * @Author suosong
 * @Date 2018/7/29
 */
public class SimpleTypeConverter  implements TypeConverter{

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requireType) {
        if(requireType == String.class){
            return (T) value;
        }
        return null;
    }
}
