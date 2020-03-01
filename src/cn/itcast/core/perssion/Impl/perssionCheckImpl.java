package cn.itcast.core.perssion.Impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.core.perssion.perssionCheck;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.service.UserService;

public class perssionCheckImpl implements perssionCheck {

	@Resource
	private UserService UserService; // 这个不能用扫描到，因为perssionCheckImpl没有在Ioc容器当中，所以我们要到application里面配置一下这个类

	@Override
	public boolean isAccessible(User user, String code) {
		// 获取用户的所有角色
		List<UserRole> list = user.getUserRoles();
		if (list == null) {
			list = UserService.getUserRolesByUserId(user.getId());
		}
		// 根据每个角色获取所有权限
		if (list != null && list.size() > 0) {
			for (UserRole ur : list) {
				Role role = ur.getId().getRole();
				for (RolePrivilege rp : role.getRolePrivileges()) {
					// 对比是否有code对应的权限
					if (code.equals(rp.getId().getCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
