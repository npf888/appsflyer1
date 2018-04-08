package com.ami.texas.condition.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.texas.condition.service.ConditionService;


/**
 * 活动配置相关
 * 
 * @author Cici
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class ConditionAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    ConditionService conditionService;

    private static Logger logger = Logger.getLogger(ConditionAction.class);

    public String queryCondition() throws APIException
    {
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = conditionService.queryActivity(pager);
        
        this.set("PAGER", pager);
               
        return "queryCondition";
    }
    
    /**
     * 跳转到添加condition页面
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toAddCondition()
    {
        
        return "toAddCondition";
    }

    /**
     * 添加触发条件/奖励
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public String doAddCondition() throws IOException
    {
        Map<String, String> response = new HashMap<String, String>();
        try
        {
       
            String insert[] = new String[]
            {"condition_id", "title",  "type", "activity_type_id"};
                      
            Map<String, Object> params = this.reqToMap(insert);
            
            if(null != this.get("description"))
            {
                params.put("description", this.get("description"));
            }
            conditionService.doAddCondition(params);
            response.put("result", "Condition Added");
        }
        catch (Exception e)
        {
            response.put("result", e.getMessage());
        }
        return "toAddCondition";
    }
    
    public void doDelete() throws IOException
    {
        String id = this.get("id");
        try{
            conditionService.doDelete(id);
            print("Condition Deleted");
        }catch(Exception e)
        {
            print("Error: " +e);
        }
    }
       
}

