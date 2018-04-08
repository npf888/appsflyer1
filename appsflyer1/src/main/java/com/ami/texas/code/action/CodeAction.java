package com.ami.texas.code.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.common.ExcelHelper;
import com.ami.texas.code.pojo.Conversioncode;
import com.ami.texas.code.pojo.ConversioncodeExcel;
import com.ami.texas.code.service.CodeService;

/**
 * 兑换码
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class CodeAction extends BaseAction{

	@Autowired
	private CodeService codeService;
	
	 public String queryCode() throws APIException
	    {
	        
	        Pager pager = new Pager(this.getReq());
	        
	        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
	        pager = codeService.queryCode(pager,this.getReq());
	        String code = this.get("code");
	        this.set("PAGER", pager);
	        this.set("code", code);
	        
	        return "queryCode";
	    }
	 public String editCode() throws APIException
	 {
		 Conversioncode one = codeService.queryOneCode(this.getReq());
		 this.set("one", one);
		 return "editCode";
	 }
	 public void toEdit() throws APIException
	 {
		 
		 codeService.toEdit(this.getReq());
	 }
	 public void toDelete() throws APIException
	 {
		 codeService.toDelete(this.getReq());
	 }
	 //导出excel
	 public void toExcel(){
		 List<Conversioncode> conversioncodeList = codeService.queryAllNotUse();
		 List<ConversioncodeExcel> conversioncodeExcelList = new ArrayList<ConversioncodeExcel>();
		 for(Conversioncode code:conversioncodeList){
			 ConversioncodeExcel conversioncodeExcel = new ConversioncodeExcel();
			 BeanUtils.copyProperties(code, conversioncodeExcel, new String[]{"id","isdelete","updateTime","createTime"});
			 conversioncodeExcelList.add(conversioncodeExcel);
			 
		 }
		 ExcelHelper.xlsxExcel2007(conversioncodeExcelList, this.getRes());
	 }
	 
}
