package cn.itcast.core.perssion;

import cn.itcast.nsfw.user.entity.User;

public interface perssionCheck {
	/**
	 * 判斷用户是不是有对应的权限
	 **/
	public boolean isAccessible(User user, String code);

}
