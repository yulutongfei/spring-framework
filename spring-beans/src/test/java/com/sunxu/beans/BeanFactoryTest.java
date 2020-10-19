package com.sunxu.beans;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/8/10 14:57
 */
public class BeanFactoryTest {

	public static void main(String[] args) {
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.addPropertyValue("helloWorld", "Hello sunxu");

		RootBeanDefinition beanDefinition = new RootBeanDefinition(HelloBean.class, null, propertyValues);

		BeanDefinitionRegistry registry = new DefaultListableBeanFactory();
		registry.registerBeanDefinition("helloWorld", beanDefinition);

		BeanFactory beanFactory = (BeanFactory) registry;
		HelloBean bean = beanFactory.getBean(HelloBean.class);
		System.out.println(bean.getHelloWorld());
	}
}
