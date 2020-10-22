package com.sunxu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 07:45
 */
@Component
public class InstanceAA implements IApi {

	@Autowired
	private InstanceBB instanceBB;

	public InstanceBB getInstanceB() {
		return instanceBB;
	}

	public void setInstanceB(InstanceBB instanceBB) {
		this.instanceBB = instanceBB;
	}

	public InstanceAA() {
		System.out.println("InstanceA实例化");
	}

	@Override
	public void say() {
		System.out.println("I'm A");
	}
}
