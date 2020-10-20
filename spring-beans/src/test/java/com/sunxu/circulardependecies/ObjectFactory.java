package com.sunxu.circulardependecies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/21 05:57
 */
@FunctionalInterface
public interface ObjectFactory<T> {

	T getObject() throws BeansException;
}
