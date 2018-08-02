package com.example.lunzi.spring.beans.propertyeditors;

import org.springframework.util.NumberUtils;

import java.beans.PropertyEditorSupport;

/**
 * 用来转化数字类型的property
 * 使用的是jdk beans包下面的成熟思路
 * @Author suosong
 * @Date 2018/7/29
 */
public class NumberPropertyEditor extends PropertyEditorSupport{

    private Class classType;

    public NumberPropertyEditor(String value ,Class classType) {
        this.classType = classType;
        super.setAsText(value);
    }

    @Override
    public Object getValue() {

        return NumberUtils.parseNumber(super.getAsText(),classType);

    }
}
