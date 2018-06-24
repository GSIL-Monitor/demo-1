package com.example.reflect;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.lucene.util.ToStringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author suosong
 * @Date 2018/6/11
 */
public class Model {
    public static void main(String[] args) throws IllegalAccessException {
        Model model = new Model();

        Map<Integer, String> sortedMap = new TreeMap<>();
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            Order annotationOrder = field.getAnnotation(Order.class);
            if (annotationOrder == null) continue;
            Suffix annotationSuffix = field.getAnnotation(Suffix.class);
            Preffix annotationPreffix = field.getAnnotation(Preffix.class);
            String value = "";
            String pre = "";
            String suffix = "";
            if (annotationPreffix != null) {
                pre = annotationPreffix.value();
            }
            if (annotationSuffix != null) {
                suffix = annotationSuffix.value();
            }
            value = field.get(model).toString();
            if (!"".equals(value) && !"0".equals(value)) {
                value = pre + value + suffix ;
            }
            sortedMap.put(annotationOrder.value(), value);
        }
        String result = "";
        for (int order : sortedMap.keySet()) {
            result = result + sortedMap.get(order) + ",";
        }
        //去掉最后一个逗号
        result = result.substring(0, result.lastIndexOf(","));
        System.out.println(result);
    }

    @Order(10)
    @Suffix("岁")
    int age = 23;


    @Order(5)
    @Preffix("${sex}在")
    String province = "山东";


}

