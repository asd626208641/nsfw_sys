package cn.itcast.nsfw.user.entity;

import java.io.Serializable;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

public class UserRole implements Serializable {

	private UserRoleId id;

	public UserRole() {
		// TODO Auto-generated constructor stub
	}

	public UserRole(UserRoleId id) {
		this.id = id;
	}

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

}
