package com.sunxu.circulardependecies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 08:29
 */
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		// 假设:A被切点命中,需要创建代理
		if (bean instanceof InstanceA) {
			return new JdkDynamicProxy(bean);
		}
		return bean;
	}
}
