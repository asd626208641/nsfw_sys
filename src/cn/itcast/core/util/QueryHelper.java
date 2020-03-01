package cn.itcast.core.util;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {

	// from子句
	private String fromClause = "";
	// where子句
	private String whereClause = "";
	// order by子句
	private String orderClause = "";

	private List<Object> parameters;

	// 排序顺序
	public static String ORDER_BY_DESC = "DESC";
	public static String ORDER_BY_ASC = "ASC";

	/**
	 * 构造from子句 
	 * clazz为实体类 
	 * alias是实体类别名
	 **/
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 构造where子句 
	 * condition查询条件语句 例如:i.title like? 
	 * params是查询条件中对应的查询值 例如："%标题%"
	 **/
	public void addCondition(String condition, Object... paramas) {
		if (whereClause.length() > 1) {
			whereClause += " AND " + condition;
		} else {
			whereClause += " WHERE " + condition;
		}

		// 设置查询条件值到查询条件里面去
		if (parameters == null) {
			parameters = new ArrayList<Object>();
		}

		if (parameters != null) {
			for (Object param : paramas) {
				parameters.add(param);
			}
		}

	}

	/**
	 * 构造orderby子句 
	 * property排序属性 例如:i.createTime 就是按照createTime来排序 
	 * order是排序类型 例如:DESC 或 ASC
	 **/
	public void addOrderByProperty(String property, String order) {
		if (orderClause.length() > 1) {
			orderClause += " , " + property + " " + order; // 非第一个排序属性
		} else {
			orderClause += " ORDER BY " + property + " " + order; // 第一个排序属性
		}
	}

	// hql查询语句
	public String getQueryListHql() {
		return fromClause+whereClause+orderClause;
	}

	// hql查询数量语句
	public String getQueryCountHql() {
		return "SELECT COUNT(*) "+fromClause+whereClause;
	}
	
	// 查询语句中？对应查询条件的集合
	public List<Object> getParameters() {
		return parameters;
	}
}
