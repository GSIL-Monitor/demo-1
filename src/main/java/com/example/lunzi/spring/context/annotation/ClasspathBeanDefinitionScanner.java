package com.example.lunzi.spring.context.annotation;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.lunzi.spring.beans.factory.support.BeanNameGenerator;
import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.core.io.support.PackageResourceLoader;
import com.example.lunzi.spring.core.io.type.AnnotationMetadata;
import com.example.lunzi.spring.core.io.type.ClassMetadata;
import com.example.lunzi.spring.core.io.type.SimpleMetadataReader;
import com.example.lunzi.spring.stereotypa.Component;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 扫描包
 *
 * @Author suosong
 * @Date 2018/8/11
 */
public class ClasspathBeanDefinitionScanner {

    BeanDefinitionRegistry beanDefinitionRegistry;

    public ClasspathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.beanDefinitionRegistry = registry;
    }

    /**
     * 扫描包路径
     *
     * @param packageToScan
     */
    public Set<BeanDefinition> doScan(String packageToScan) throws IOException {

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        String[] basePackages = packageToScan.split(",");
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponets(basePackage);
            beanDefinitions.addAll(candidates);
            for (BeanDefinition beanDefinition : candidates) {
                beanDefinitionRegistry.registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
            }
        }
        return beanDefinitions;
    }


    Set<BeanDefinition> findCandidateComponets(String basePackage) throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources(basePackage);
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        if (resources == null || resources.length == 0) return candidates;

        for (Resource resource : resources) {
            SimpleMetadataReader reader = new SimpleMetadataReader(resource);
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
            ClassMetadata classMetadata = reader.getClassMetadata();
            if (annotationMetadata.hasAnnotation(Component.class.getName())) {

                String beanClassName = classMetadata.getClassName();
                BeanDefinition beanDefinition = new ScannedGenericBeanDefinition( annotationMetadata);
                beanDefinition.setBeanClassName(beanClassName);
                BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
                String beanName = beanNameGenerator.generateBeanName(beanDefinition,beanDefinitionRegistry);//创建bean名称
                beanDefinition.setBeanName(beanName);

                candidates.add(beanDefinition);
            }
        }
        return candidates;
    }
}
