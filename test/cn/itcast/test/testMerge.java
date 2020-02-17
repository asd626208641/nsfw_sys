package cn.itcast.test;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.test.entity.Person;
import cn.itcast.test.service.TestService;

class testMerge {
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testHibernate() {
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction trs = session.beginTransaction();
		session.save(new Person("ds1233"));
		trs.commit();
		session.close();
	}

	@Test
	public void testServiceAndDao() {
		TestService ts = (TestService) ctx.getBean("testService");
		ts.say();
		ts.save(new Person("啊啊1111"));
		
	}
}
