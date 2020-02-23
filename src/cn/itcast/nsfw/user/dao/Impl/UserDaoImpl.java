package cn.itcast.nsfw.user.dao.Impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE ACCOUNT=?";
		if (StringUtils.isNotBlank(id)) {
			hql += " AND ID!=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			query.setParameter(1, id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(String id) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=? ");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		Query query = getSession().createQuery("FROM UserRole WHERE id.userId=? ");
		query.setParameter(0, id);
		query.executeUpdate();
		return query.list();
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		Query query = getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}
}
