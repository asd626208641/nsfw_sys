package cn.itcast.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.pageResult;
import cn.itcast.core.util.QueryHelper;

public interface BaseDao<T> {
	// 新增
	public void save(T entity);

	// 更新
	public void update(T entity);

	// 根据id删除
	public void delete(Serializable id);

	// 根据id查找
	public T findObjectById(Serializable id);

	// 查找列表
	public List<T> findObjects();

	// 条件查询
	public List<T> findObjects(String hql, List<Object> parameters);

	// 條件查询 --查询助手queryHelper
	public List<T> findObjects(QueryHelper queryHelper);

	// 根据分页查询
	public pageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);

}
