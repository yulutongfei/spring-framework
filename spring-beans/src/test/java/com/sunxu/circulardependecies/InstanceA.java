package com.sunxu.circulardependecies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 07:45
 */
@Component
@Scope("prototype")
public class InstanceA implements IApi{

	@Autowired
	private InstanceB instanceB;

	public InstanceB getInstanceB() {
		return instanceB;
	}

	public void setInstanceB(InstanceB instanceB) {
		this.instanceB = instanceB;
	}

	public InstanceA() {
		System.out.println("InstanceA实例化");
	}

	@Override
	public void say() {
		System.out.println("I'm A");
	}
}
