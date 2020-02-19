package cn.itcast.nsfw.user.dao;

import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User> {
	/**
	 *根據用戶Id和账号来查询用户 
	 */
	List<User> findUserByAccountAndId(String id, String account);
	
	
}
