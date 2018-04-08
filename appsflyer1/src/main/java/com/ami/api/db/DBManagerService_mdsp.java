
package com.ami.api.db;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;

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
public class DBManagerService_mdsp extends DBManagerService_base
{
    protected DataSource dataSource = null;

    public DBManagerService_mdsp()
        throws APIException
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
   
}
