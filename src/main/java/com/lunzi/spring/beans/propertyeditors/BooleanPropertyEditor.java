package com.lunzi.spring.beans.propertyeditors;

import java.beans.PropertyEditorSupport;

/**
 * 用来转化property的bool属性
 * @Author suosong
 * @Date 2018/7/29
 */
public class BooleanPropertyEditor extends PropertyEditorSupport{

    public BooleanPropertyEditor(String value) {
        super.setAsText(value);
    }

    @Override
    public Object getValue() {
        return Boolean.parseBoolean(super.getAsText());
    }
}
