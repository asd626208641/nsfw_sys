package cn.itcast.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	/**
	 *根據用戶Id和账号来查询用户 
	 */
	public List<User> findUserByAccountAndId(String id, String account);
	//保存用户角色
	public void saveUserRole(UserRole userRole);
	
	//根据用户id删除所有用户角色信息
	public void deleteUserRoleByUserId(Serializable id);
	
	//根据用户id删除所有用户角色信息
	public List<UserRole> getUserRolesByUserId(String id);

	//根据用户账号和密码查询用户信息
	public List<User> findUserByAccountAndPass(String account, String password);
	
}
