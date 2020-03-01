package cn.itcast.nsfw.complain.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.page.PageResult;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;

public class ComplainAction extends BaseAction {

	@Resource
	private ComplainService complainService;
	private Complain complain;

	// 列表
	public String listUI() {
		// 加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", complain.COMPLAIN_STATE_MAP);
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		PageResult pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		
		System.out.println(pageResult.getItems());
		
		return "listUI";
	}

	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

}
