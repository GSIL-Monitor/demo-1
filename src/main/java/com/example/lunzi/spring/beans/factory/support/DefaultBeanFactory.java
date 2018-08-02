package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.config.ConfigurableBeanFactory;
import com.example.lunzi.spring.beans.factory.config.DefaultSingletonBeanRegistry;
import com.example.lunzi.spring.beans.factory.config.PropertyValue;
import com.example.lunzi.spring.utils.ClassUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.Introspector;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
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

    BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);

    //BeanFactory接口定义
    @Override
    public Object getBean(String name) {

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if (beanDefinition == null) return null;
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

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object bean = this.instantiateBean(beanDefinition);
        this.populate(bean,beanDefinition);
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
     *
     * @param beanDefinition
     */
    private void populate(Object bean,BeanDefinition beanDefinition) throws InvocationTargetException, IllegalAccessException {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();
        if (propertyValueList == null || propertyValueList.size() == 0) return;

        Map<String,Object> map = new HashMap<>(16);
        for(PropertyValue propertyValue : propertyValueList){
            Object value = propertyValue.getValue();
            Object resolverValue = resolver.resolveValueIfNecessary(value);
            map.put(propertyValue.getName(),resolverValue);

            //操作propertyValue的其他字段
            //PropertyEditor
        }

        //也可以用java 的 Introspector来做，也挺简单的。这里就用成熟的第三方包了。
        BeanUtils.populate(bean,map);
    }

}
