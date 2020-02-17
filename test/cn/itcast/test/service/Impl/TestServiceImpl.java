package cn.itcast.test.service.Impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.Person;
import cn.itcast.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	TestDao testDao;

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("service say hi");
	}

	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		testDao.save(person);
	}

	@Override
	public Person findPerson(Serializable id) {
		// TODO Auto-generated method stub
		return testDao.findPerson(id);
	}

}
