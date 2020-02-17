package cn.itcast.test.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.test.service.TestService;


public class TestAction extends ActionSupport {
	@Autowired
	TestService testService;

	public String execute() {
		return "success";
	}
}
