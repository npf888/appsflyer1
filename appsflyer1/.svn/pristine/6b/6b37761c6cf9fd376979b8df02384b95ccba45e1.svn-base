package com.ami.api.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共分页设置
 * 
 * @author wjh
 * 
 */
public class Pager
{
    
    // 总记录数
    private int totalCount = 0;
    
    // 开始行号
    private int start = 0;
    
    // 分页条数 默认10
    private int limit = 50;
    
    // 当前页
    private int currentPage = 1;
    
    // 页面数
    private int totalPage = 0;
    
    // 显示出几页可以点击
    private int pages = 15;
    
    /**
     * 分页html
     */
    private String pageHtml;
    
    // 结果接
    List Items = new ArrayList();
    
    public Pager()
    {
        
    }
    
    public Pager(HttpServletRequest request)
    {
        setLimit(request);
        
        setCurrentPage(request);
        
    }
    
    public void setCurrentPage(HttpServletRequest request)
    {
        String currentPagestr = request.getParameter("currentPage");
        
        int currentPage = 1;
        try
        {
            currentPage = Integer.parseInt(currentPagestr.trim());
            
            this.currentPage = currentPage;
            
        }
        catch (Exception e)
        {
            
        }
        
        this.start = (this.currentPage - 1) * limit;
    }
    
    public void setStart(HttpServletRequest request)
    {
        String startstr = request.getParameter("start");
        
        int start = 0;
        try
        {
            start = Integer.parseInt(startstr.trim());
            
            this.start = start;
        }
        catch (Exception e)
        {
            
        }
        
    }
    
    public void setLimit(HttpServletRequest request)
    {
        String limitstr = request.getParameter("limit");
        
        int limit = 1;
        try
        {
            limit = Integer.parseInt(limitstr.trim());
            
            if (limit == 0)
            {
                limit = 1;
            }
            
            this.limit = limit;
        }
        catch (Exception e)
        {
            
        }
        
    }
    
    public int getTotalCount()
    {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount)
    {
        if (totalCount > 0)
        {
            this.totalCount = totalCount;
            this.totalPage = totalCount / limit;
            if (totalCount % limit > 0)
            {
                this.totalPage++;
            }
        }
        else
        {
            this.totalCount = 0;
            this.totalPage = 0;
        }
        
        setPageHtml();
    }
    
    public int getTotalPage()
    {
        return totalPage;
    }
    
    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }
    
    public int getPages()
    {
        return pages;
    }
    
    public void setPages(int pages)
    {
        this.pages = pages;
    }
    
    public String getPageHtml()
    {
        return pageHtml;
    }
    
    public void setPageHtml(String pageHtml)
    {
        this.pageHtml = pageHtml;
    }
    
    public int getLimit()
    {
        return limit;
    }
    
    public List getItems()
    {
        return Items;
    }
    
    public void setItems(List items)
    {
        Items = items;
    }
    
    public int getStart()
    {
        return start;
    }
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void setPageHtml()
    {
        
        int start;
        
        int end;
        
        currentPage = currentPage > totalPage ? totalPage : currentPage;
        
        StringBuffer sb = new StringBuffer();
        if (totalPage <= 1)
        {
            this.pageHtml = "";
            return;
        }
        
        sb.append("<div class='pagination' style='margin:0px'><ul>");
        
        // 如果总的页数小于总的要显示 页数 则start 从1 开始
        if (totalPage <= pages)
        {
            start = 1;
        }
        else if (currentPage > totalPage - pages)
        {
            start = totalPage - pages - 1;
        }
        else
        {
            // 当前页减去 5 作为 起始页
            start = currentPage - (pages - 1) / 2 <= 0 ? 1 : currentPage - (pages - 1) / 2;
        }
        // 如果开始也大于第一页 而且总的页数大于要显示的页数 则显示出前一页
        if (start > 1 && totalPage > pages)
        {
            sb.append("<li><a href='#' onclick=\"go(1)\"><<</a></li>");
        }
        // 终止页 为 起始页 加上总的要显示的页数
        end = start + pages - 1;
        
        if (end > totalPage || currentPage >= totalPage || currentPage > (totalPage - pages))
        {
            end = totalPage;
        }
        
        for (int i = start; i <= end; i++)
        {
            if (i == currentPage)
            {
                sb.append("<li class='active'>");
            }
            else
            {
                sb.append("<li>");
            }
            sb.append("<a   onclick=\"go(" + i + ")\" >").append(i).append("</a></li>");
        }
        
        // 如果开始也大于第一页 而且总的页数大于要显示的页数 则显示出前一页
        if (currentPage <= totalPage - pages)
        {
            sb.append("<li><a  onclick=\"go(" + totalPage + ")\" >>></a></li>");
        }
        
        sb.append("</ul></div>");
        
        this.pageHtml = sb.toString();
    }
    
    public int getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public void setStart(int start)
    {
        this.start = start;
    }
    
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    
    //把两个pager融合成一个
    public Pager add(Pager pager2)
    {
        List list1 = this.getItems();
        List list2 = pager2.getItems();
        list1.addAll(list2);
        this.setItems(list1);
        this.setLimit(50);
        this.setTotalCount(this.getTotalCount() + pager2.getTotalCount());
        
        return this;
    }
    
}