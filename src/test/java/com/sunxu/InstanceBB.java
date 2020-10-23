package com.sunxu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 07:46
 */
@Component
public class InstanceBB {

	@Autowired
	private InstanceAA instanceAA;

	public InstanceAA getInstanceA() {
		return instanceAA;
	}

	public void setInstanceA(InstanceAA instanceAA) {
		this.instanceAA = instanceAA;
	}

	public InstanceBB() {
		System.out.println("InstanceB实例化");
	}
}
