package com.ami.api.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;

@Component
public class Table_Mysql
{
    
    @Autowired
    private DBManagerService_Mysql mysql_db;
    
    /**
     * 鎻掑叆鏁版嵁
     * 
     * @param tab 琛ㄦ槑
     * @param arr 瑕佹彃鍏ョ殑瀛楁 鏁版嵁
     * @param val 鍙傛暟
     * @throws APIException
     * @throws APIException
     * @throws APIException
     * @throws APIException
     */
    public int insert(String tab, Map<String, Object> filed)
        throws APIException, APIException, APIException
    {
        
        // String tab = "wxjs.tart";
        // insert into ssss(33,33,) values (?,?);
        
        StringBuffer sql = new StringBuffer("insert into ");
        
        sql.append(tab);
        
        sql.append("( ");
        
        Object prams[] = new Object[filed.size()];
        StringBuffer p = new StringBuffer();
        
        int i = 0;
        for (String f : filed.keySet())
        {
            sql.append(f).append(",");
            prams[i++] = filed.get(f);
            p.append("?").append(",");
        }
        
        sql.deleteCharAt(sql.length() - 1);
        p.deleteCharAt(p.length() - 1);
        
        sql.append(")").append(" values (").append(p).append(")");
        
        return this.mysql_db.update(sql.toString(), prams);
        
    }
    
    /**
     * 鎻掑叆鏁版嵁
     * 
     * @param tab 琛ㄦ槑
     * @param arr 瑕佹彃鍏ョ殑瀛楁 鏁版嵁
     * @param val 鍙傛暟
     * @throws APIException
     * @throws APIException
     * @throws APIException
     * @throws APIException
     */
    public int insertF(String tab, Map<String, Object> parameters)
        throws APIException, APIException, APIException
    {
        String sql = getInsertSql(tab, parameters);
        
        return this.mysql_db.update(sql);
        
    }
    
    public String getInsertSql(String tab, Map<String, Object> parameters)
    {
        StringBuffer sql = new StringBuffer("insert into ");
        sql.append(tab);
        sql.append(" ( ");
        
        StringBuffer temp_value = new StringBuffer(" ( ");
        for (String string : parameters.keySet())
        {
            sql.append(string);
            sql.append(",");
            convertData(temp_value, parameters.get(string));
            temp_value.append(",");
        }
        
        sql = new StringBuffer(sql.substring(0, sql.length() - 1));
        temp_value = new StringBuffer(temp_value.subSequence(0, temp_value.length() - 1));
        
        sql.append(" ) values ");
        temp_value.append(" ); ");
        
        sql.append(temp_value);
        return sql.toString();
    }
    
    public void delete(String tableName, Object id)
        throws APIException
    {
        String sql = "delete from " + tableName + " where id = ?";
        
        this.mysql_db.update(sql, new Object[]
        {id});
    }
    
    /**
     * 琛ㄦ槑
     * 
     * @param tableName 琛ㄦ槑
     * @param value id鐨勫��
     * @return Map<String, Object>
     * @throws APIException
     */
    public Map<String, Object> load(String tableName, Object value)
        throws APIException
    {
        Map<String, Object> par = new HashMap<String, Object>();
        par.put("id", value);
        
        return this.load(tableName, par);
    }
    
    /**
     * 
     * @param tableName 琛ㄦ槑
     * @param condition 鏌ヨ鏉′欢 key 瀛楁 value
     * @return
     * @throws APIException
     */
    public Map<String, Object> load(String tableName, Map<String, Object> condition)
        throws APIException
    {
        return this.load(tableName, null, condition);
    }
    
    /**
     * 
     * @param tableName 琛ㄦ槑
     * @param field 闇�瑕佹煡璇㈢殑鍏蜂綋瀛楁
     * @param condition 鏌ヨ鏉′欢 map key 瀛楁 value
     * @return
     * @throws APIException
     */
    public Map<String, Object> load(String tableName, String field[])
        throws APIException
    {
        return load(tableName, field, null);
    }
    
    /**
     * 
     * @param tableName 琛ㄦ槑
     * @param field 闇�瑕佹煡璇㈢殑鍏蜂綋瀛楁
     * @param condition 鏌ヨ鏉′欢 map key 瀛楁 value
     * @return
     * @throws APIException
     */
    public Map<String, Object> load(String tableName, String field[], Map<String, Object> condition)
        throws APIException
    {
        
        if (StringUtils.isEmpty(tableName))
        {
            return null;
        }
        
        StringBuffer sql = new StringBuffer(" select  ");
        
        if (null == field)
        {
            sql.append(" * ");
            
        }
        else
        {
            String fields = StringTool.arryToString(field, ",");
            sql.append(fields);
        }
        
        sql.append(" from ").append(tableName).append(" ");
        
        if (null != condition && condition.size() >= 1)
        {
            sql.append(" where 1=1 ");
            
            for (String string : condition.keySet())
            {
                sql.append(" and ");
                
                Object value = condition.get(string);
                
                if (value instanceof Integer || value instanceof Long)
                {
                    sql.append(string).append("=").append(" ").append(value).append(" ;");
                }
                else
                {
                    sql.append(string).append("=").append(" '").append(value).append("' ;");
                }
                
            }
        }
        
        return this.mysql_db.load(sql.toString());
        
    }
    
    public int update(String tab, Map<String, Object> parameters, String idValue)
        throws APIException
    {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", idValue);
        return this.update(tab, parameters, condition);
    }
    
    public int update(String tab, Map<String, Object> parameters, long idValue)
        throws APIException
    {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", idValue);
        return this.update(tab, parameters, condition);
    }
    
    public int update(String tab, Map<String, Object> filed, Map<String, Object> condition)
        throws APIException
    {
        StringBuffer sql = new StringBuffer("update ");
        
        sql.append(tab).append(" set ");
        
        Object prams[] = new Object[filed.size() + condition.size()];
        
        int i = 0;
        for (String f : filed.keySet())
        {
            sql.append(f).append("=").append("?").append(",");
            prams[i++] = filed.get(f);
        }
        
        sql.deleteCharAt(sql.length() - 1);
        
        sql.append(" where ");
        
        for (String f : condition.keySet())
        {
            sql.append(f).append("=").append("?").append(",");
            prams[i++] = condition.get(f);
        }
        sql.deleteCharAt(sql.length() - 1);
        
        return this.mysql_db.update(sql.toString(), prams);
        
    }
    
    /**
     * 鏌ヨ
     * 
     * 缁撴灉瀛樺偍 key 涓篒D,value 涓鸿褰曠粨鏋�
     * 
     * @param sql
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Map<String, Map<String, Object>> query(String sql)
        throws APIException
    {
        List<HashMap<String, Object>> list_map = null;
        
        HashMap<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        
        list_map = this.mysql_db.query(sql);
        
        if (null != list_map)
        {
            for (HashMap<String, Object> hashMap : list_map)
            {
                map.put(StringTool.getValue(hashMap.get("id")), hashMap);
            }
        }
        
        return map;
    }
    
    private void convertData(StringBuffer sb, Object o)
    {
        if (null == o)
        {
            sb.append("''");
            
        }
        else if (o instanceof String)
        {
            if (StringTool.isEmpty((String)o))
            {
                sb.append("' '");
            }
            else
            {
                sb.append("'");
                sb.append(o.toString());
                sb.append("'");
            }
            
        }
        else
        {
            sb.append(o.toString());
            
        }
    }
    
    public static void main(String[] args)
    {
        String tab = "wxjs.tart";
        // insert into ssss(33,33,) values (?,?);
        
        Map<String, Object> filed = new HashMap<String, Object>();
        filed.put("name", "123232");
        filed.put("trw", 1);
        
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", "123232");
        
        StringBuffer sql = new StringBuffer("update ");
        
        sql.append(tab).append(" set ");
        
        Object prams[] = new Object[filed.size() + condition.size()];
        
        int i = 0;
        for (String f : filed.keySet())
        {
            sql.append(f).append("=").append("?").append(",");
            prams[i++] = filed.get(f);
        }
        
        sql.deleteCharAt(sql.length() - 1);
        
        sql.append(" where ");
        
        for (String f : condition.keySet())
        {
            sql.append(f).append("=").append("?").append(",");
            prams[i++] = condition.get(f);
        }
        sql.deleteCharAt(sql.length() - 1);
        
        System.out.println(sql);
    }
    
}
