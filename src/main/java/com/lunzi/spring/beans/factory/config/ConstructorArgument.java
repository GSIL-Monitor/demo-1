package com.lunzi.spring.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 构造器
 * @Author suosong
 * @Date 2018/8/5
 */
public class ConstructorArgument {

    List<ValueHoder> valueHoders = new ArrayList<>();

    public List<ValueHoder> getValueHoders() {
        return valueHoders;
    }

    public void addArgument(ValueHoder valueHoder){
        valueHoders.add(valueHoder);
    }

    public boolean isEmpty(){
        return this.valueHoders.size() == 0;
    }


    public static class ValueHoder {
        String name;//属性名
        Object value;

        public ValueHoder(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

}
