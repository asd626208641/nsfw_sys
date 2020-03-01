package cn.itcast.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
	private RolePrivilegeId id;    //要生成一個联合主键类
	
	public RolePrivilege() {
		// TODO Auto-generated constructor stub
	}
	
	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
	
	
}
