package cn.itcast.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.core.Constant.Constant;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

public class LoginAction extends ActionSupport {

	@Resource
	private UserService userService;
	private User user;
	private String loginResult;

	// 跳转到登录页面
	public String toLoginUI() {
		return "loginUI";
	}

	// 登录
	public String login() {
		if (user != null) {
			if (StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())) {
				// 根据用户的账号和密码查询用户列表
				List<User> list = userService.findUserByAccountAndPass(user.getAccount(), user.getPassword());
				if (list != null && list.size() > 0) {
					// 登录成功
					User user = list.get(0);
					// 根据用户id查询该用户的角色
					user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
					// 将信息保存到session中
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					// 将用户登录记录到日志里面
					Log log = LogFactory.getLog(getClass());
					log.info("用户名称为:" + user.getName() + "的用户登录了系统");
					// 重定向到首页中
					return "home";
				} else {
					loginResult = "账号或密码不成功";
				}
			} else {
				loginResult = "账号或密码不能为空";
			}
		} else {
			loginResult = "请输入账号和密码";
		}
		return toLoginUI(); // 当账号或密码不成功或为空或输入账号密码时失败都返回页面，成功则不会执行这个跳转
	}

	public String logout() {
		ServletActionContext.getContext().getSession().remove(Constant.USER);
		return toLoginUI();
	}

	public String toNoPermissionUI() {
		return "NoPermissionUI";
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
}
