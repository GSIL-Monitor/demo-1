package com.spring.lifecycle;

/**
 * @Author suosong
 * @Date 2018/9/28
 * spring 生命周期
 *
 * 01  想在bean实例化结束跟销毁的时候调用代码，可以让bean实现 InitializingBean,DisposableBean 两个接口。在Lifecycle01中有
 * 02  想在bean初始化完成之前跟之后执行一段代码，可以用spring提供的BeanPostProcessor,这个到底是怎么执行的，可以看源码
 *     学到了一个看源码的技能，debug。然后看调用栈。
 *     依赖注入完毕了，才会调用这些东西。可以查看源码的populate方法
 *
 * 03  BeanPostProcessor在spring底层的应用，很有用处
 *      比如@Autoware注解的解析 @Async注解的解析，@PostConstructor 等都是用的BeanPostProcessor
 */
public class Lifecycle {

}
