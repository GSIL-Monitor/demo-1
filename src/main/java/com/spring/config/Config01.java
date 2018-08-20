package com.spring.config;


import org.springframework.context.annotation.*;
import com.spring.bean.Person;
/**
 * @Author suosong
 * @Date 2018/8/14
 */
@Configuration
/**
 * 只有设置了useDefaultFilters = false ，过滤器才能起作用,当设置为false，而又没有设置过滤器时，是不会进行包扫描的，切记
 *
 */
@ComponentScan(basePackages = {"com.spring"},useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)
        }
)
public class Config01 {

    @Bean("person")
    @Scope("singleton")
    @Lazy//懒加载
    public Person person01(){
        //System.out.println("===person 被创建了");
        return new Person("张三",30);
    }

    @Bean("window")
    @Conditional(value = {WindowsCondition.class})
    public Person person03(){
        //System.out.println("===person 被创建了");
        return new Person("bill gates",30);
    }

    /**
     * springboot 内部大量使用了@Conditional注解
     * 符合condition条件才注册，否则不注册
     * 此注解也可以放在配置类上，意为符合此条件的，所有的@Bean都扫描。不合符的，所有的@Bean不扫描
     */
    @Conditional(value = {LinuxCondition.class})
    @Bean("linux")
    public Person person02(){
        //System.out.println("===person 被创建了");
        return new Person("linus",30);
    }



}
