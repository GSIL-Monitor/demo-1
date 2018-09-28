package com.lunzi.spring.aop;

import com.lunzi.spring.aop.aspectj.AspectJBeforeAdvice;
import com.lunzi.spring.aop.aspectj.AspectJExpressionPointCut;
import com.lunzi.spring.aop.config.MethodLocatingFactory;
import com.lunzi.spring.aop.framework.ReflectiveMethodInvocation;
import com.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.lunzi.spring.beans.factory.BeanFactory;
import com.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lunzi.spring.beans.test.Person;
import com.lunzi.spring.beans.test.TransactionManager;
import com.lunzi.spring.context.ApplicationContext;
import com.lunzi.spring.context.support.ClasspathXmlApplicationContext;
import com.lunzi.spring.core.io.ClassPathResource;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/9/21
 */
public class TestAop01 {
    //@Before


    @Test
    public void test() {
        ApplicationContext applicationContext = new ClasspathXmlApplicationContext("spring/spring-context-aop.xml");
        Person person = (Person) applicationContext.getBean("person");
        person.save();
    }

    /**
     * 测试判断切点
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void test_PointCut() throws NoSuchMethodException {
        String expression = "execution(* com.lunzi.spring.beans.config.Person.save(..))";
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut();
        pointCut.setExpression(expression);
        Class targetClass = Person.class;
        Method method = targetClass.getMethod("save");
        Assert.assertTrue(pointCut.getMethodMatcher().matches(method));
    }

    /**
     * 测试拿到Method
     */
    @Test
    public void test_getMethod() throws Exception {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("spring/spring-context-aop.xml"));

        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setTargetBeanName("txManager");
        methodLocatingFactory.setBeanFactory(beanFactory);


        Method method = (Method) methodLocatingFactory.getObject();

        Assert.assertTrue(method.equals(TransactionManager.class.getMethod("start")));
    }

    /**
     * 测试调用
     */
    @Test
    public void test_invocation() throws Exception {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("spring/spring-context-aop.xml"));
        /**
         * 切点
         */
        String expression = "execution(* com.lunzi.spring.beans.config.Person.save(..))";
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut();
        pointCut.setExpression(expression);
        /**
         * 拦截器数组
         */
        List<MethodInterceptor> methodInterceptors = new ArrayList<>();
        /**
         * 拦截器 工厂
         */
        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setTargetBeanName("txManager");
        methodLocatingFactory.setBeanFactory(beanFactory);
        Method beforeMethod = (Method) methodLocatingFactory.getObject();
        methodInterceptors.add(new AspectJBeforeAdvice(beforeMethod,pointCut,beanFactory.getBean("txManager")));

        /*ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(beanFactory.getBean("person")
        ,)*/
    }

    /**
     * cglib的测试
     */
    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new org.springframework.cglib.proxy.MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("被拦截了。。。");
                return methodProxy.invokeSuper(o,objects);
            }
        });
        Person person = (Person)enhancer.create();
        person.save();
    }
}
