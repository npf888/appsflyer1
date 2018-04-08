package com.ami.sys.config.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.config.service.ConfigService;

@Scope("prototype")
@Component
public class ConfigAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log logger = LogFactory.getLog(ConfigAction.class);
    
    @Autowired
    ConfigService configService;
    
    /**
     * 查询
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String dictionary()
    {
        
        try
        {
            
            // 查询所有的字典配置项
            List<HashMap<String, Object>> list = configService.queryDictionary_type();
            this.set("dictionary_type", list);
            
            // data
            String dtypeid = this.get("dtypeid");
            
            String field[] = new String[]
            {"dtypeid", "dtitle"};
            
            Map<String, String> param = this.reqToStrMap(field);
            this.set("params", param);
            
            Pager pager = new Pager(this.getReq());
            
            if (StringTool.isEmpty(dtypeid))
            {
                dtypeid = list.get(0).get("dtypeid").toString();
                param.put("dtypeid", dtypeid);
            }
            
            pager = configService.queryDictionary(param, pager);
            
            this.set("PAGER", pager);
            
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        
        return "dictionary_list";
    }
    
    /**
     * 编辑
     * 
     * @throws IOException
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doAddDictionary()
        throws IOException, APIException
    {
        // // 注：此字段 需要和页面的 表单的 name 以及 数据库的字段名 都要保持一致
        // 1， 定制需要往数据库更新的字段
        String field[] = new String[]
        {"dtype","dtitle"};
        
        Map<String, Object> parameters = this.reqToMap(field);
        
        
        configService.doAddsysDictionary(parameters);
        
        this.set("id", "id");
        
        print("success");
    }
    
    
    /**
     * 编辑
     * 
     * @throws IOException
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doEditDictionary()
        throws IOException, APIException
    {
        // // 注：此字段 需要和页面的 表单的 name 以及 数据库的字段名 都要保持一致
        // 1， 定制需要往数据库更新的字段
        String field[] = new String[]
        {"dtitle"};
        
        Map<String, Object> parameters = this.reqToMap(field);
        
        String id = this.get("id");
        
        configService.update(parameters, id);
        
        this.set("id", "id");
        
        print("success");
    }
    
    /**
     * 删除用户
     */
    public void doDelete()
        throws Exception
    {
        String id = this.get("id");
        
        configService.delByID(id);
        
        print("success");
    }
}
