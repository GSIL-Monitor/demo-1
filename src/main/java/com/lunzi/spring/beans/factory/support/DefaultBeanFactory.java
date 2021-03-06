package com.lunzi.spring.beans.factory.support;

import com.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.lunzi.spring.beans.factory.BeanCreationException;
import com.lunzi.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.lunzi.spring.beans.factory.config.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    ClassLoader beanClassLoader;//Bean加载器

    BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
    ConstructorResolver constructorResolver = new ConstructorResolver(this);

    List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    //BeanFactory接口定义
    @Override
    public Object getBean(String name) {

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        try {
            Object bean = null;
            String beanClassName = beanDefinition.getBeanClassName();
            //如果是单例的，从单例接口中拿
            if (beanDefinition.isSingleton()) {
                bean = this.getSingleton(beanClassName);
                if (bean == null) {
                    bean = createBean(beanDefinition);
                    this.registerSingleton(beanClassName, bean);
                }
            } else {

                bean = createBean(beanDefinition);

            }
            return bean;
        } catch (Exception e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }
    }

    //BeanDefinitionRegistry接口定义
    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    //BeanDefinitionRegistry接口定义
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    //ConfigurableBeanFactory接口定义
    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }

    //ConfigurableBeanFactory接口定义
    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public Object resolveDependency(DependencyDescriptor dependencyDescriptor) throws ClassNotFoundException {
        Class classToMatch = dependencyDescriptor.getDependencyType();
        for (BeanDefinition bd : beanDefinitionMap.values()) {
            resolveBeanClass(bd, this.getBeanClassLoader());//确保bd的beanClass属性是有值的
            if (classToMatch.isAssignableFrom(bd.getBeanClass())) {//参数跟他一样，或者是其子类
                return this.getBean(bd.getBeanName());
            }
        }
        return null;
    }

    @Override
    public Class getType(String beanName) throws NoSuchBeanDefinitionException {
        BeanDefinition bd = this.getBeanDefinition(beanName);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException("");
        }
        try {
            this.resolveBeanClass(bd, this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bd.getBeanClass();
    }

    private void resolveBeanClass(BeanDefinition bd, ClassLoader beanClassLoader) throws ClassNotFoundException {
        if (bd.hasBeanClass()) {
            return;
        }
        bd.resolveBeanClass(beanClassLoader);
    }

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object bean = null;
        if (beanDefinition.hasConstructorArgumentValues()) {//配置了构造函数
            bean = constructorResolver.autowireConstructor(beanDefinition);
        } else {
            bean = this.instantiateBean(beanDefinition);
        }
        //依赖注入
        this.populate(bean, beanDefinition);
        return bean;
    }

    /**
     * 将bean实例化
     *
     * @param beanDefinition
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object instantiateBean(BeanDefinition beanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return this.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName()).newInstance();
    }

    /**
     * 依赖注入,用了apache commons-beans包
     * spring源码中使用了专门的Convert类，比较复杂
     * <p>
     * 1，进行autowired注入
     * 2，进行setter 注入
     *
     * @param beanDefinition
     */
    private void populate(Object bean, BeanDefinition beanDefinition) throws InvocationTargetException, IllegalAccessException {
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessors) {
            if (beanPostProcessor instanceof AutowiredAnnotationBeanPostProcessor) {
                AutowiredAnnotationBeanPostProcessor processor = (AutowiredAnnotationBeanPostProcessor) beanPostProcessor;
                processor.postProcessPropertyValues(bean, beanDefinition.getBeanName());
            }
        }

        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        if (propertyValueList == null || propertyValueList.size() == 0) {
            return;
        }
        for (PropertyValue propertyValue : propertyValueList) {
            String propertyName = propertyValue.getName();
            Object value = propertyValue.getValue();
            Object resolverValue = valueResolver.resolveValueIfNecessary(value);
            BeanUtils.setProperty(bean, propertyName, resolverValue);
            //操作propertyValue的其他字段
            //PropertyEditor
        }
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public static void main(String[] args) {
        System.out.println(DefaultBeanFactory.class.isAssignableFrom(Object.class));
        System.out.println(Object.class.isAssignableFrom(DefaultBeanFactory.class));
    }

}
