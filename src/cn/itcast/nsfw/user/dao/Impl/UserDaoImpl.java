package cn.itcast.nsfw.user.dao.Impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account)  {
		String hql = "FROM User WHERE ACCOUNT=?";
		if (StringUtils.isNotBlank(id)) {
			hql += " AND ID=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			query.setParameter(1, id);
		}
		return query.list();
	}
}
