package cn.itcast.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
  * 写这个类的作用是当发生异常时候，不想要它返回到一个全局的jsp页面，通过这种方式可以指定返回的特定jsp页面
 *  所以，我们前面写的是全局异常映射，就是所有代码发生异常都采取同一种执行方式，而这个类的作用则是指定单个异常的解决方案
 * **/
public class SysResultAction extends StrutsResultSupport {   

	@Override
	protected void doExecute(String arg0, ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) invocation.getAction(); //为什么要写这句代码和声明BaseAction类作用是什么，暂时我还是没有理解到，等以后在补充
		
		//do something
		System.out.println("进入了SysResultAction类中，可以进行其他操作");
		
	}

}
