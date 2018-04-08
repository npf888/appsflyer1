package com.ami.api.db;

/**
 * SQLUtils utils = new SQLUtils(User.class);
		utils.setWhereStr("", "id", "=", 100).setWhereStr("and", "name", " ",
				"is null").setWhereStr("and", "date", ">=", new Date());
		utils.setOrderByStr("id", "desc").setOrderByStr("name", "asc");
		System.out.println(utils.buildSelectSQL());
		System.out.println(utils.buildCountSQL());
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SqlUtils {

	private String beanName;
	private String beanShortName;
	private Map<String, Object> propertyMap;
	private List<String> conditionList;
	private List<String> relationList;
	private Map<String, String> orderByMap;

	public SqlUtils(Class<?> instance) {
		this.setBeanName(instance.getSimpleName());
		this.setBeanShortName(Character.toLowerCase(this.getBeanName()
				.charAt(0))
				+ "");
		init();
	}
	public SqlUtils(String tableName) {
		//this.setBeanName(instance.getSimpleName());
		this.setBeanShortName(tableName);
		init();
	}

	public SqlUtils() {
		init();
	}
	
	void init(){
		propertyMap = new LinkedHashMap<String, Object>();
		conditionList = new LinkedList<String>();
		relationList = new LinkedList<String>();
		orderByMap = new LinkedHashMap<String, String>();
	}

	/**
	 * 添加查询条件
	 * 
	 * @param relation
	 *            关联 "and","or"等
	 * @param property
	 *            查询的对象属性
	 * @param condition
	 *            查询的条件，关系符
	 * @param value
	 *            查询的值
	 */
	public SqlUtils setWhereStr(String relation, String property,
			String condition, Object value) {
		if(value != null){
			relationList.add(relation);
			propertyMap.put(property, value);
			conditionList.add(condition);
		}
		
		return this;
	}
	
	/**
	 * 添加查询条件
	 * 
	 * @param relation
	 *            关联 "and","or"等
	 * @param property
	 *            查询的对象属性
	 * @param condition
	 *            查询的条件，关系符
	 * @param value
	 *            查询的值
	 */
	public SqlUtils setWhereStr(String property, Object value) {
		if(value != null){
			this.setWhereStr("and",property,"=",value);
		}
		
		return this;
	}


	private String buildWhereStr() {
		StringBuffer buffer = new StringBuffer();
		if (!propertyMap.isEmpty() && propertyMap.size() > 0) {
			buffer.append("WHERE 1 = 1 ");
			int index = 0;
			for (String property : propertyMap.keySet()) {
				if (property != null && !property.equals("")) {
					buffer.append(relationList.get(index));
					buffer.append(" ").append(this.getBeanShortName()).append(
							".").append(property).append(" ").append(
							conditionList.get(index)).append(" ").append(
							getValue(propertyMap.get(property))).append(" ");
				}
				index++;
			}
		}

		return buffer.toString();
	}

	private String getValue(Object object) {
		if (object.toString().equals("is null")
				|| object.toString().equals("is not null")
				|| object.toString().equals("?")) {
			return object.toString();
		} else if (object.getClass().equals(String.class)) {
			return "'" + object.toString() + "'";
		} else if (object.getClass().equals(Date.class)
				|| object.getClass().equals(Timestamp.class)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return "to_date('" + sdf.format(object)
					+ "','yyyy-mm-dd hh24:mi:ss')";
		}
		return object.toString();
	}

	/**
	 * 创建SQL
	 * @return String
	 */
	public String buildSelectSQL() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ").append(this.getBeanShortName()).append(
				" FROM ").append(this.getBeanName()).append(" ").append(
				this.getBeanShortName()).append(" ").append(buildWhereStr())
				.append(this.getOrderByStr());
		return buffer.toString().trim();
	}

	/**
	 * 创建COUNT（*）的语句
	 * @return String
	 */
	public String buildCountSQL() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(*) ").append(this.getBeanShortName())
				.append(" FROM ").append(" ")
				.append(this.getBeanShortName()).append(" ").append(
						buildWhereStr());
		return buffer.toString().trim();
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanShortName() {
		return beanShortName;
	}

	public void setBeanShortName(String beanShortName) {
		this.beanShortName = beanShortName;
	}

	private String getOrderByStr() {
		StringBuffer buffer = new StringBuffer();
		if (!orderByMap.isEmpty() && orderByMap.size() > 0) {
			buffer.append("ORDER BY ");
			int index = 0;
			for (String orderBy : orderByMap.keySet()) {
				if (index != 0) {
					buffer.append(",");
				}
				buffer.append(this.getBeanShortName()).append(".").append(
						orderBy).append(" ").append(orderByMap.get(orderBy));
				index++;
			}
		}
		return buffer.toString();
	}

	/**
	 * 设置ORDER BY的key = value
	 * @param orderByStr
	 * @param order
	 * @return String
	 */
	public SqlUtils setOrderByStr(String orderByStr, String order) {
		orderByMap.put(orderByStr, order);
		return this;
	}

	public SqlUtils setTableName(String tableName) {
		this.setBeanName(tableName);
		return this;
	}
	
	public static void main(String[] args)
	{
		SqlUtils utils = new SqlUtils("User");
		
		utils.setWhereStr( "id",  100).setWhereStr("and", "name", " ",
				"is null").setWhereStr("and", "date", ">=", new Date());
		utils.setOrderByStr("id", "desc").setOrderByStr("name", "asc");
		System.out.println(utils.buildSelectSQL());
		
		System.out.println(utils.buildCountSQL());
	}

}

