package com.ami.api.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;

/**
 * 数据库操作类
 * <p>
 * Description:数据库访问接口实现类，涉及到表的增,删,改,查全部通过该类实现
 * <p>
 * (数据库连接池的方式)
 * <p>
 * <p>
 * </p>
 * <p>
 * 
 * </p>
 * 
 * @author
 * @version
 */
@Component
public class DBManagerService
{
    
    private static final long serialVersionUID = -5876980456199846559L;
    
    private static final Log logger = LogFactory.getLog(DBManagerService.class);
    
    private static String DB_ERROR = "000l";
    
    // DataSource对象，用于提供数据库连接
    private static DataSource dataSource = null;
    
    /**
     * 默认构造函数
     * 
     * @throws APIException 初始化数据源失败
     */
    public DBManagerService()
        throws APIException
    {
        
    }
    
    /**
     * 设置一个数据源 <功能详细描述>
     * 
     * @param dataSource
     * @see [类、类#方法、类#成员]
     */
    public void setDataSource(DataSource dataSource)
    {
        DBManagerService.dataSource = dataSource;
    }
    
    /**
     * 获取一个连接
     * 
     * @return Connection
     * @throws APIException 数据库操作异常
     */
    public Connection getConnection()
        throws APIException
    {
        
        try
        {
            return dataSource.getConnection();
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
    }
    
    /**
     * 根据sql进行查询，返回的结果是存放的Map实 例（key为字段名称，value为字段对应的具体值）的集合。
     * 
     * @param sql sql 查询的sql字符串
     * @return List 存放的Map实例（key为字段名称，value为字段对应的具体值）的集合
     * @throws APIException 数据库操作异常
     */
    
    public List<HashMap<String, Object>> query(String sql)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new MapListHandler();
        List<HashMap<String, Object>> result = null;
        try
        {
            result = (List<HashMap<String, Object>>)qr.query(sql, rh);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        return result;
    }
    
    public List<Map<String, String>> queryToString(String sql)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new MapListHandler();
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        try
        {
            List<Map<String, Object>> list = (List<Map<String, Object>>)qr.query(sql, rh);
            
            for (Map<String, Object> map : list)
            {
                Map<String, String> temp = new HashMap<String, String>();
                
                for (String key : map.keySet())
                {
                    temp.put(key, StringTool.getValue(map.get(key)));
                }
                
                result.add(temp);
            }
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        return result;
    }
    
    /**
     * 根据sql进行查询，返回的结果是存放的Map实 例（key为字段名称，value为字段对应的具体值）的集合。
     * 
     * @param sql 查询的sql字符串
     * @param paras 查询的sql字符串中要匹配的参数数组
     * @return List 存放的Map实例（key为字段名称，value为字段对应的具体值）的集合
     * @throws APIException 数据库操作异常
     */
    
    public List<HashMap<String, Object>> query(String sql, Object[] paras)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new MapListHandler();
        List<HashMap<String, Object>> result = null;
        try
        {
            
            result = (List)qr.query(sql, paras, rh);
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        return result;
    }
    
    /**
     * 返回一条结果的查询,建议使用该方法获取序列或表中某一个字段的值；取值方式为map.get(map.keySet().iterator().next
     * ()).toString
     * 
     * @param sql 查询的sql字符串
     * @return Map key为字段名称，value为字段对应的具体值
     * @throws APIException 数据库操作异常
     */
    
    public Map<String, Object> load(String sql)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new MapHandler();
        Map<String, Object> resultMap = null;
        try
        {
            
            resultMap = (Map<String, Object>)qr.query(sql, rh);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
        return resultMap;
    }
    
    /**
     * 根据多个查询参数查询一条结果
     * 
     * @param sql 查询的sql字符串
     * @param paras 查询的sql字符串中要匹配的参数数组
     * @return Map key为字段名称，value为字段对应的具体值
     * @throws APIException 数据库操作异常
     */
    
    public Map<String, Object> load(String sql, Object[] paras)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new MapHandler();
        Map<String, Object> resultMap = null;
        try
        {
            
            resultMap = (Map<String, Object>)qr.query(sql, paras, rh);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
        return resultMap;
    }
    
    /**
     * 根据查询字符串、指定的的数据类型、指定的参数类型进行查询。
     * 
     * @param sql 查询的sql字符串
     * @param paras 查询的sql字符串中要匹配的参数数组
     * @param type 查询的数据类型
     * @return List 查询的结果，是指定type类型的集合
     * @throws APIException 数据库操作异常
     */
    
    public <T> List<T> query(String sql, Object[] paras, Class<T> type)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new BeanListHandler(type);
        List<Object> result = null;
        try
        {
            
            result = (List<Object>)qr.query(sql, paras, rh);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
        return (List<T>)result;
    }
    
    /**
     * 根据查询字符串、指定的的数据类型进行查询。
     * 
     * @param sql 查询的sql字符串
     * @param type 查询的数据类型
     * @return List 查询的结果，是制定type类型的集合
     * @throws APIException 数据库操作异常
     */
    
    public <T> List<T> query(String sql, Class<T> type)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new BeanListHandler(type);
        List<T> result = null;
        
        try
        {
            result = (List<T>)qr.query(sql, rh);
            
        }
        catch (SQLException ex)
        {
            logger.error("sql:" + sql);
            throw new APIException(DB_ERROR, ex);
        }
        
        return result;
    }
    
    /**
     * 根据查询字符串、指定的的数据类型、指定的参数进行查询。
     * 
     * @param sql 查询的sql字符串
     * @param paras 查询的sql字符串中要匹配的参数数组
     * @param type 指定查询的数据类型
     * @return Object 查询结果
     * @throws APIException 数据库操作异常
     */
    
    public <T> T load(String sql, Object[] paras, Class<T> type)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new BeanHandler(type);
        Object result = null;
        try
        {
            
            result = qr.query(sql, paras, rh);
            
        }
        catch (SQLException ex)
        {
            logger.error(ex.toString());
            
            throw new APIException(DB_ERROR, ex.toString());
        }
        
        return (T)result;
    }
    
    /**
     * 根据查询字符串、指定的的数据类型进行查询。
     * 
     * @param sql 查询的sql字符串
     * @param type 指定查询的数据类型对象
     * @return Object 查询指定数据类型集合
     * @throws APIException 数据库操作异常
     */
    
    public <T> T load(String sql, Class<T> type)
        throws APIException
    {
        
        QueryRunner qr = new QueryRunner(dataSource);
        ResultSetHandler rh = new BeanHandler(type);
        Object result = null;
        try
        {
            
            result = qr.query(sql, rh);
            
        }
        catch (SQLException ex)
        {
            
            logger.error("sql:" + sql);
            throw new APIException(DB_ERROR, ex);
        }
        
        return (T)result;
    }
    
    /**
     * 根据更新sql字符串进行更新。
     * 
     * @param sql 更新操作的sql字符串
     * @return int 更新的记录数
     * @throws APIException 数据库操作异常
     */
    public int update(String sql)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        int update = 0;
        try
        {
            
            update = qr.update(sql);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
        return update;
    }
    
    /**
     * 根据更新sql字符串，指定的参数进行更新。
     * 
     * @param sql 更新操作的sql字符串
     * @param param 更新参数
     * @return int 更新的记录数
     * @throws APIException 数据库操作异常
     */
    public int update(String sql, Object param)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        int num = 0;
        try
        {
            
            num = qr.update(sql, param);
            
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        return num;
    }
    
    /**
     * 根据更新sql字符串，指定的参数数组进行更新。
     * 
     * @param sql 更新操作的sql字符串
     * @param params 更新参数
     * @return int 更新的记录数
     * @throws APIException 数据库操作异常
     */
    public int update(String sql, Object[] params)
        throws APIException
    {
        QueryRunner qr = new QueryRunner(dataSource);
        int num = 0;
        try
        {
            
            num = qr.update(sql, params);
            
        }
        catch (SQLException ex)
        {
            throw new APIException(DB_ERROR, ex);
        }
        
        return num;
    }
    
    /**
     * 根据更新sql字符串，指定的参数数组进行批量更新(不包含事物处理)。 该方法只能是用于纵表的插入操作
     * 
     * @param sql 更新操作的sql字符串
     * @param params 更新参数[二维数组]
     * @return int[] 操作成功数组里保存的是值全为-2的一维数组，数组长度是行的长度。
     * @throws APIException 数据库操作异常
     */
    public int[] updateBatch(String sql, Object[][] params)
        throws APIException
    {
        
        QueryRunner qr = new QueryRunner(dataSource);
        int num[] =
        {};
        try
        {
            
            num = qr.batch(sql, params);
        }
        catch (SQLException ex)
        {
            
            throw new APIException(DB_ERROR, ex);
        }
        
        return num;
    }
    
    /**
     * 执行数据库存贮过程，返回由存贮过程返回的结果集，如果存储过程没有返回值则返回空Map。 调用该存储过程的时候
     * 
     * @param procName 存贮过程名称(如果存储过程带包名，则存储过程名为：包名.存储过程名)
     * @param params Object[]，存贮过程的输入参数，参数是一个一维数组(包括输入和输出)
     * @return Map:如果存储过程带返回值，返回值(返回游标)以[每列数据为一个map]存在List中,
     *         如果存储过程不带返回值则返回空List.
     * @throws APIException 表示执行存储过程失败，抛出数据库操作异常。
     */
    public List<Map<String, Object>> execCurProcedure(String procName, Object[] params)
        throws APIException
    {
        // 申请一个数据库连接
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet resultSet = null;
        try
        {
            
            conn = new DBManagerService().getConnection();
            
            // 先搞定存储过程调用SQL形式如："call procName(?,?)"
            StringBuffer sql = new StringBuffer();
            sql.append("{call " + procName + "(");
            for (int i = 0; i < params.length; i++)
            {
                if (i != 0)
                {
                    sql.append(',');
                }
                sql.append('?');
            }
            sql.append(")}");
            // 得到CallableStatement对象
            cs = conn.prepareCall(sql.toString());
            
            HashMap<Integer, Object> outParaMap = new HashMap<Integer, Object>();
            // 对于类型为IN的参数，通过setXXX()方法来设置参数；OUT类型的参数必须被注册
            for (int i = 0; i < params.length; i++)
            {
                
                // 参数支持4种类型:String,Integer,Double,Date
                if (setPara(cs, params[i], i))
                {
                    continue;
                }
                // 参数支持类型:OutputPara
                if (params[i] instanceof OutputPara)
                {
                    OutputPara para = (OutputPara)params[i];
                    cs.registerOutParameter(i + 1, para.getSqlTypes());
                    outParaMap.put(Integer.valueOf(String.valueOf(i + 1)), para);
                }
                else
                {
                    
                    throw new IllegalArgumentException("The type of params is wrong");
                }
            }
            // 执行存储过程
            cs.execute();
            
            Object[] key = outParaMap.keySet().toArray();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            
            for (int i = 0; i < key.length; i++)
            {
                Integer idx = (Integer)key[i];
                Object value = cs.getObject(idx.intValue());
                resultSet = (ResultSet)value;
                
                while (resultSet.next())
                {
                    
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int numberOfColumns = rsmd.getColumnCount();
                    
                    for (int j = 0; j < numberOfColumns; j++)
                    {
                        resultMap.put(String.valueOf(j + 1), resultSet.getObject(j + 1));
                    }
                    resultList.add(resultMap);
                }
            }
            
            return resultList;
        }
        catch (Exception e)
        {
            
            throw new APIException(DB_ERROR, e);
        }
        finally
        {
            this.closeResource(resultSet, cs, conn);
        }
    }
    
    /**
     * 执行数据库存贮过程，返回由存贮过程返回的基本数据类型的输出参数，如果存储过程没有返回值则返回空Map。 调用该存储过程的时候
     * 
     * @param procName 存贮过程名称(如果存储过程带包名，则存储过程名为：包名.存储过程名)
     * @param params Object[]，存贮过程的输入参数，参数是一个一维数组(包括输入和输出)
     * @return Map:如果存储过程带返回值，返回值(基本数据)以[参数序号：返回结果]存在map中, 如果存储过程不带返回值则返回空Map.
     * @throws APIException 表示执行存储过程失败，抛出数据库操作异常。
     */
    public Map<String, Object> execBasicProce(String procName, Object[] params)
        throws APIException
    {
        // 申请一个数据库连接
        Connection conn = null;
        CallableStatement cs = null;
        try
        {
            
            conn = new DBManagerService().getConnection();
            
            // 先搞定存储过程调用SQL形式如："call procName(?,?)"
            StringBuffer sql = new StringBuffer();
            sql.append("{call " + procName + "(");
            for (int i = 0; i < params.length; i++)
            {
                if (i != 0)
                {
                    sql.append(',');
                }
                sql.append('?');
            }
            sql.append(")}");
            // 得到CallableStatement对象
            cs = conn.prepareCall(sql.toString());
            
            HashMap<Integer, Object> outParaMap = new HashMap<Integer, Object>();
            // 对于类型为IN的参数，通过setXXX()方法来设置参数；OUT类型的参数必须被注册
            for (int i = 0; i < params.length; i++)
            {
                // 参数支持4种类型:String,Integer,Double,Date
                if (setPara(cs, params[i], i))
                {
                    continue;
                }
                
                // 参数支持类型OutputPara
                if (params[i] instanceof OutputPara)
                {
                    OutputPara para = (OutputPara)params[i];
                    cs.registerOutParameter(i + 1, para.getSqlTypes());
                    outParaMap.put(Integer.valueOf(String.valueOf(i + 1)), para);
                }
                else
                {
                    
                    throw new IllegalArgumentException("The type of params is wrong");
                }
            }
            // 执行存储过程
            cs.execute();
            
            Object[] key = outParaMap.keySet().toArray();
            HashMap<String, Object> returnMap = new HashMap<String, Object>();
            
            for (int i = 0; i < key.length; i++)
            {
                Integer idx = (Integer)key[i];
                OutputPara para = (OutputPara)outParaMap.get(idx);
                Object value = cs.getObject(idx.intValue());
                String paraStr = String.valueOf(i);
                if (null != para.getName())
                {
                    paraStr = para.getName();
                }
                
                returnMap.put(paraStr, value);
            }
            
            return returnMap;
        }
        catch (Exception e)
        {
            
            throw new APIException(DB_ERROR, e);
        }
        finally
        {
            this.closeResource(null, cs, conn);
        }
    }
    
    /**
     * 释放资源
     * 
     * @param rs 结果集，
     * @param st sql语句对象，没有可以传空
     * @param conn 连接对象，没有可以传空
     * @throws APIException 数据库链接异常
     */
    public void closeResource(ResultSet rs, Statement st, Connection conn)
        throws APIException
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                
                throw new APIException(DB_ERROR, e);
            }
            
        }
        
        if (st != null)
        {
            try
            {
                st.close();
            }
            catch (SQLException ex1)
            {
                
                throw new APIException(DB_ERROR, ex1);
                
            }
            
        }
        
        if (conn != null)
        {
            
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                
                throw new APIException(DB_ERROR, ex);
            }
            
        }
        
    }
    
    /**
     * 移除List列表所有项(可能为List<HashMap<String,Object>>或者List<Object>)
     * 
     * @param list ListList列表
     */
    public void clearList(List<?> list)
    {
        if (list != null && !list.isEmpty())
        {
            for (int i = 0; i < list.size(); i++)
            {
                Object obj = list.get(i);
                if (obj instanceof Map)
                {
                    ((Map<?, ?>)obj).clear();
                }
                else
                {
                    obj = null;
                }
                list.remove(i);
            }
            
        }
    }
    
    /**
     * 移除Map中所有映射关系HashMap<String, Object>
     * 
     * @param map HashMap所有映射关系
     */
    public void clearMap(Map<String, Object> map)
    {
        if (map != null && !map.isEmpty())
        {
            map.clear();
        }
    }
    
    /**
     * 存储过程设置参数
     * 
     * @param cs CallableStatement对象
     * @param params 输入参数
     * @param i 输入参数
     * @return CallableStatement
     * @throws SQLException
     */
    private boolean setPara(CallableStatement cs, Object params, int i)
        throws SQLException
    {
        // 用来判断是否和下列每种情况匹配
        boolean isExist = false;
        
        // 参数支持4种类型:String,Integer,Double,Date
        if (params instanceof String)
        {
            String tmp = toDB((String)params);
            cs.setString(i + 1, tmp);
            isExist = true;
        }
        else if (params instanceof Integer)
        {
            Integer tmp = (Integer)params;
            cs.setInt(i + 1, tmp.intValue());
            isExist = true;
        }
        else if (params instanceof Double)
        {
            Double tmp = (Double)params;
            cs.setDouble(i + 1, tmp.doubleValue());
            isExist = true;
        }
        else if (params instanceof Date)
        {
            Date tmp = (Date)params;
            cs.setDate(i + 1, tmp);
            isExist = true;
        }
        
        return isExist;
    }
    
    public String toDB(String text)
    {
        int l = text.length();
        StringBuffer strb = new StringBuffer();
        for (int i = 0; i < l; i++)
        {
            char c = text.charAt(i);
            if (c == '\'')
            {
                strb.append('\'').append('\'');
            }
            else
            {
                strb.append(c);
            }
        }
        return strb.toString();
    }
    
    public Pager queryPage(String sql, Pager pager, Class<?> type)
        throws APIException
    {
        return this.queryPage(sql, null, pager, type);
    }
    
    public Pager queryPage(String sql, Object[] paras, Pager pager)
        throws APIException
    {
        return this.queryPage(sql, paras, pager, null);
    }
    
    /**
     * 
     * @param sql
     * @param paras
     * @param pager
     * @param type
     * @return
     * @throws APIException
     */
    public Pager queryPage(String sql, Object[] paras, Pager pager, Class<?> type)
        throws APIException
    {
        // TODO Auto-generated method stub
        if (sql != null)
        {
            StringBuffer currentList = new StringBuffer("");
            StringBuffer count = new StringBuffer("");
            count.append("SELECT COUNT(*) AS total FROM (");
            count.append(sql);
            count.append(") AS a");
            Map countMap = this.load(count.toString(), paras);
            Object total = countMap.get("total");
            if (total != null)
            {
                pager.setTotalCount(new Integer(total.toString()));
            }
            
            currentList.append("SELECT a.*  FROM ( ");
            currentList.append(sql);
            currentList.append(") AS a limit ? offset ?");
            
            logger.debug("pageSQL:" + currentList.toString());
            
            if (null == paras)
            {
                paras = new Object[0];
                
            }
            
            Object parasobj[] = new Object[paras.length + 2];
            
            for (int i = 0; i < paras.length; i++)
            {
                parasobj[i] = paras[i];
            }
            parasobj[paras.length] = pager.getLimit();
            parasobj[paras.length + 1] = pager.getStart();
            
            if (null != type)
            {
                pager.setItems(this.query(currentList.toString(), parasobj, type));
                
            }
            else
            {
                pager.setItems(this.query(currentList.toString(), parasobj));
            }
            
        }
        else
        {
            return null;
        }
        return pager;
    }
    
    public List executeQuery(String sql, List columnNames)
        throws java.sql.SQLException
    {
        Connection conn = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;
        java.util.List result = new java.util.ArrayList();
        try
        {
            logger.debug("executeQuery sql: " + sql);
            conn = this.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            java.sql.ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (columnNames != null)
            {
                columnNames.clear();
                for (int i = 1; i <= columnCount; i++)
                {
                    columnNames.add(metaData.getColumnName(i).toString());
                }
            }
            while (rs.next())
            {
                String[] value = new String[columnCount];
                for (int i = 0; i < columnCount; i++)
                {
                    value[i] = rs.getString(i + 1);
                    if (value[i] != null)
                        value[i] = value[i].trim();
                }
                result.add(value);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
}
