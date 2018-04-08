package com.ami.api.web.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.db.DBManagerService;
import com.ami.api.db.Table;
import com.ami.api.exception.APIException;

@Component
public abstract class BaseDao
{
    @Autowired
    protected DBManagerService db;
    
    @Autowired
    protected Table table;
    
    /**
     * 获取序列
     * 
     * @param seqid
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public long getSequence(String seqid)
        throws APIException
    {
        
        Map<String, Object> m = db.load("SELECT nextval('" + seqid + "') as seq");
        
        long llreturn = Long.parseLong(m.get("seq").toString());
        
        return llreturn;
    }
    
}
