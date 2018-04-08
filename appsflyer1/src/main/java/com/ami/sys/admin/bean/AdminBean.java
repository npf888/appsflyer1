package com.ami.sys.admin.bean;

/**
 * 用户实体类
 * 
 * @author zhang
 * 
 */
public class AdminBean
{
    
    private String id;
    
    /**
     * 账号
     */
    private String account;
    
    /**
     * 密码
     */
    private String pwd;
    
    /**
     * 用户中文名字
     */
    private String username;
    
    /**
     * email
     */
    private String email;
    
    
    private String department;
    
    private String unit;
    
    private String phone;
    
    
    private String idcard;
    
    private String grouplist;
    
    private String global_app_id;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getAccount()
    {
        return account;
    }
    
    public void setAccount(String account)
    {
        this.account = account;
    }
    
    public String getPwd()
    {
        return pwd;
    }
    
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getGrouplist()
    {
        return grouplist;
    }
    
    public void setGrouplist(String grouplist)
    {
        this.grouplist = grouplist;
    }
    
    public String getDepartment()
    {
        return department;
    }
    
    public void setDepartment(String department)
    {
        this.department = department;
    }
    
    public String getUnit()
    {
        return unit;
    }
    
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getGlobal_app_id()
    {
        return global_app_id;
    }

    public void setGlobal_app_id(String global_app_id)
    {
        this.global_app_id = global_app_id;
    }
    
    
}
