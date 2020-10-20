package com.sunxu.circulardependecies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 孙许
 * @version 1.0
 * @date 2020/10/20 07:46
 */
@Component
public class InstanceB {

	@Autowired
	private InstanceA instanceA;

	public InstanceA getInstanceA() {
		return instanceA;
	}

	public void setInstanceA(InstanceA instanceA) {
		this.instanceA = instanceA;
	}

	public InstanceB() {
		System.out.println("InstanceB实例化");
	}
}
