package cn.itcast.core.service.Impl;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.core.page.PageResult;
import cn.itcast.core.service.BaseService;
import cn.itcast.core.util.QueryHelper;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) { //这个baseDao是从service中传过来的，而不是容器中获取的，所以不用加@resource属性
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		// TODO Auto-generated method stub
		return baseDao.findObjects();
	}

	public List<T> findObjects(String hql, List<Object> parameters) {
		return baseDao.findObjects(hql, parameters);
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getPageResult(queryHelper,pageNo,pageSize);
	} 

}
