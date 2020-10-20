package com.sunxu.circulardependecies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 07:48
 */
public class MainStart {

	private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	public static void loadBeanDefinitions() {
		RootBeanDefinition aBeanDefinition = new RootBeanDefinition(InstanceA.class);
		RootBeanDefinition bBeanDefinition = new RootBeanDefinition(InstanceB.class);
		beanDefinitionMap.put("instanceA", aBeanDefinition);
		beanDefinitionMap.put("instanceB", bBeanDefinition);
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// 加载beanDefinition
		loadBeanDefinitions();
		// 注册Bean的后置处理器

		// 循环创建bean
		for (String key : beanDefinitionMap.keySet()) {
			// 先创建A
			Object bean = getBean(key);
			if (bean instanceof InstanceA) {
				InstanceA instanceA = (InstanceA) bean;
				instanceA.say();
			}
		}
	}

	// 一级缓存: 成熟的Bean
	public static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	// 二级缓存: 为了将成熟Bean和我们纯净Bean分离,避免读取到不完整的Bean
	public static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

	// 三级缓存:

	// 获取bean
	public static Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
		Object singleton = getSingleton(beanName);
		if (singleton != null) {
			return singleton;
		}

		// 实例化
		RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
		Class<?> beanClass = beanDefinition.getBeanClass();
		// 通过无参构造函数
		Object instanceBean = beanClass.newInstance();

		// 创建动态代理(耦合, BeanPostProcessor)
		// spring还是希望正常的Bean还是在初始化后创建动态代理
		// 只在循环依赖的情况下在实例化后创建动态代理
		// 判断当前是不是循环依赖
		new JdkProxyBeanPostProcessor().getEarlyBeanReference(instanceBean, beanName);

		// 添加到二级缓存
		earlySingletonObjects.put(beanName, instanceBean);
		// 属性赋值
		Field[] declaredFields = beanClass.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			Autowired annotation = declaredField.getAnnotation(Autowired.class);
			// 说明属性上面有Autowired
			if (annotation != null) {
				declaredField.setAccessible(true);
				// instanceB
				String filedName = declaredField.getName();
				Object fieldObject = getBean(filedName);
				declaredField.set(instanceBean, fieldObject);
			}
		}
		// 初始化

		return instanceBean;
	}

	public static Object getSingleton(String beanName) {
		if (singletonObjects.containsKey(beanName)) {
			return singletonObjects.get(beanName);
		} else if (earlySingletonObjects.containsKey(beanName)) {
			return earlySingletonObjects.get(beanName);
		}
		return null;
	}
}
