package com.spring.di;

/**
 * @Author suosong
 * @Date 2018/9/28
 * spring的依赖注入，可以使用@Autowire 跟 @Resource.但是这两者也是有区别的
 *
 * @Autowire 注解
 *      默认使用类型去容器中查找
 *      如果找到了多个，则根据属性名字作为id，去调用getBean(beanName)去查找。
 *      如果不想使用类型去查找，那么就要配合@Qualifier去使用
 * @Resource （JSR250规范） 或者@Inject( JSR330规范)
 *
 */
public class DI {
}
