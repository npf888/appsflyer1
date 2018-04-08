package com.ami.bazoo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.bazoo.pojo.BazooNewGuy;
import com.ami.bazoo.service.BazooNewGuyService;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class BazooNewGuyAction extends BaseAction{

		@Autowired
		private BazooNewGuyService BazooNewGuyService;
		
		
		public String getBazooNewGuy(){
			List<BazooNewGuy> bazooNewGuyList = BazooNewGuyService.getBazooNewGuy();
			this.set("newGuys", bazooNewGuyList);
			return "list";
		}
		
		
		
}
