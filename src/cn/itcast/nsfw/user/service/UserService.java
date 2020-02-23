package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.itcast.core.exception.ServiceException;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public interface UserService {
	// 新增
	public void save(User user);

	// 更新
	public void update(User user);

	// 根据id删除
	public void delete(Serializable id);

	// 根据id查找
	public User findObjectById(Serializable id);

	// 查找列表
	public List<User> findObjects() throws ServiceException;

	// 导出用户列表
	public void exportExcel(List<User> userList, ServletOutputStream outputStream);

	// 导出用户列表
	public void importExcel(File userExcel, String userExcelFileName);

	/*
	 * 根据账号和id来查找用户
	 */
	public List<User> findUserByAccountAndId(String id, String account);

	// 更新用户及其角色
	public void updateUserAndRole(User user, String... RoleId);

	// 保存用户及其角色
	public void saveUserAndRole(User user, String... RoleId);

	// 根据用户ID获取该角色信息
	public List<UserRole> getUserRolesByUserId(String id);

	// 根据用户账号和密码查询
	public List<User> findUserByAccountAndPass(String account, String password);
}
