package com.ami.api.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.db.DBManagerService_Mysql;
import com.ami.api.db.Table_Mysql;

@Component
public abstract class BaseMysqlDao
{
    @Autowired
    protected DBManagerService_Mysql db;
    
    @Autowired
    protected Table_Mysql table;
    
}
