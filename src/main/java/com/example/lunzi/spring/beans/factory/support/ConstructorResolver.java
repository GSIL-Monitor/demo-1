package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.config.ConfigurableBeanFactory;
import com.example.lunzi.spring.beans.factory.config.ConstructorArgument;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 找到一个合适的构造函数，执行，返回bean
 * 这个地方用到了一点算法
 *
 * @Author suosong
 * @Date 2018/8/5
 */
public class ConstructorResolver {

    ConfigurableBeanFactory factory;
    BeanDefinitionValueResolver valueResolver;

    public ConstructorResolver(ConfigurableBeanFactory factory) {
        this.factory = factory;
        this.valueResolver = new BeanDefinitionValueResolver(factory);
    }



    /**
     * 这里只是针对有name属性的情况，正在的spring实现要比这个复杂的多
     *
     * @param bd
     * @return
     */
    public Object autowireConstructor(BeanDefinition bd) {
        Constructor constructorToUser = null;
        Object[] paramsToUser = null;
        Class beanClass = null;
        try {
            beanClass = factory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException("create bean error", e);
        }

        Constructor[] candidates = beanClass.getConstructors();
        ConstructorArgument constructorArgument = bd.getConstructorArgument();
        for (Constructor constructor : candidates) {
            Class[] paramClasses = constructor.getParameterTypes();
            List<ConstructorArgument.ValueHoder> valueHoders = constructorArgument.getValueHoders();
            if (constructor.getParameterCount() != valueHoders.size()) continue;
            constructorToUser = constructor;
            paramsToUser = new Object[constructor.getParameterCount()];
            for (int i = 0; i < paramsToUser.length; i++) {

                //jdk很难获取参数名称，下面的作废
                   /* if(constructorParams[i].getName().equals(valueHoders.get(j).getName())) {
                        paramsToUser[i] = valueResolver.resolveValueIfNecessary(valueHoders.get(j).getValue());
                        break;
                    }*/
                Object parseObj = valueResolver.resolveValueIfNecessary(valueHoders.get(i).getValue());
                if (ClassUtils.isAssignable(paramClasses[i],parseObj.getClass())) {//右边转化为左边
                    paramsToUser[i] = parseObj;
                }else if(parseObj instanceof String){//parseObj要么是自定义类型，要么是String类型
                    //这里需要TypeConverter做很多条件的分析，非常复杂。这里就简化，只做Integer的判断
                    if(Integer.class == paramClasses[i] || int.class == paramClasses[i]){
                        paramsToUser[i] = (Object) Integer.parseInt(parseObj.toString());
                    }
                }

            }
        }

        //检验构造函数参数对象列表是否都被填充
        for (int i = 0; i < paramsToUser.length; i++) {
            if (paramsToUser[i] == null)
                throw new BeanCreationException("create bean error", null);
        }

        try {
            return constructorToUser.newInstance(paramsToUser);
        } catch (Exception e) {
            throw new BeanCreationException("create bean error", e);
        }

    }

}
