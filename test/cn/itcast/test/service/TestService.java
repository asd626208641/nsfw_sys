package cn.itcast.test.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.itcast.test.entity.Person;

public interface TestService {
	public void say();

	// 保存
	public void save(Person person);

	// 根据id查询人员
	public Person findPerson(Serializable id);

}
