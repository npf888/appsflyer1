package com.ami.sys.right.bean;

import java.util.List;

/**
 * 
 * 菜单bean
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RightBean
{
    /**
     * sid
     */
    private String sid;
    
    /**
     * pid
     */
    private String pid;
    
    /**
     * 名称
     */
    private String title;
    
    /**
     * 孩子子节点
     */
    private List<RightBean> children;
    
    /**
     * 排序
     */
    private int displayorder;
    
    /**
     * 跳转的URL
     */
    private String url;
    
    /**
     * frame name
     */
    private String target;
    
    /**
     * 类型 -1 菜单的叶子结点， 0 主菜单 1 按钮
     */
    private String ntype;
    
    public String getSid()
    {
        return sid;
    }
    
    public void setSid(String sid)
    {
        this.sid = sid;
    }
    
    public String getPid()
    {
        return pid;
    }
    
    public void setPid(String pid)
    {
        this.pid = pid;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public List<RightBean> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<RightBean> children)
    {
        this.children = children;
    }
    
    public int getDisplayorder()
    {
        return displayorder;
    }
    
    public void setDisplayorder(int displayorder)
    {
        this.displayorder = displayorder;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getNtype()
    {
        return ntype;
    }
    
    public void setNtype(String ntype)
    {
        this.ntype = ntype;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }
    
}
