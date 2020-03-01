package cn.itcast.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
	private String roleId;
	private String name;
	private String state;
	private Set<RolePrivilege> rolePrivileges;  //我们用的是多对多有中间表的方法，当然多对多关系实现也可以不用中间表也可以实现

	// 用户状态
	public static String USER_STATE_VALID = "1";
	public static String USER_STATE_INVALID = "0";

	public Role() {
	}

	public Role(String roleId) {
		this.roleId=roleId;
	}
	
	public Role(String roleId, String name, String state, Set<RolePrivilege> rolePrivileges) {
		this.roleId = roleId;
		this.name = name;
		this.state = state;
		this.rolePrivileges = rolePrivileges;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}
