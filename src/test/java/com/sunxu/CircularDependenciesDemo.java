package com.sunxu;

import org.springframework.context.annotation.*;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/21 10:25
 */
@Configuration
@Import({InstanceAA.class, InstanceBB.class})
public class CircularDependenciesDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(CircularDependenciesDemo.class);
		context.refresh();
		InstanceAA bean = context.getBean(InstanceAA.class);
		bean.say();
		context.close();
	}

}
