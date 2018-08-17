package com.lunzi.spring.stereotypa;

import java.lang.annotation.*;


@Target(ElementType.TYPE)//只能放在类上，枚举上或者接口上
@Retention(RetentionPolicy.RUNTIME)//保留到运行时
@Documented//可以生成java文档
public @interface Component {

    String value() default "";

}
