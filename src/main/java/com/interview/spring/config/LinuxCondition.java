package com.interview.spring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author suosong
 * @Date 2018/8/15
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        String osName = context.getEnvironment().getProperty("os.name");
        System.out.println("osName="+osName);
        if(osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac"))
            return true;
        return false;
    }
}
