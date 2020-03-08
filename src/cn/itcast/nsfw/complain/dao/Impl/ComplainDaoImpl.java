package cn.itcast.nsfw.complain.dao.Impl;

import java.util.List;

import org.apache.poi.hssf.record.LeftMarginRecord;
import org.hibernate.SQLQuery;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.complain.dao.ComplainDao;
import cn.itcast.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		String sql = "SELECT imonth,c2 FROM tmonth LEFT JOIN "
				+ "(SELECT month(comp_time) c1,COUNT(comp_id) c2 FROM complain WHERE year(comp_time)=? GROUP BY month(comp_time)) t "
				+ "ON imonth=c1 ORDER BY imonth";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		sqlQuery.setParameter(0, year);
		return sqlQuery.list();
	}
}
