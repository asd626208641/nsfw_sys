package cn.itcast.home.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

public class HomeAction extends ActionSupport {
	@Resource
	private UserService userService;

	private Map<String, Object> return_map;

	public String execute() {
		return "home";
	}

	public String complainAddUI() {
		return "complainAddUI";
	}

	public String getUserJson() {
		try {
			// 1、获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if (StringUtils.isNotBlank(dept)) {
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" + dept);
				// 2、根据部门查询用户列表
				return_map = new HashMap<String, Object>();
				return_map.put("msg", "success");
				return_map.put("userList", userService.findObjects(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

}
