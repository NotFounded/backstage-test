package com.ruoyi.web.controller.activity;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.ActivityComment;
import com.ruoyi.system.service.IActivityCommentService;
import com.ruoyi.system.service.IActivityService;
import com.vdurmont.emoji.EmojiParser;

/**
 * 留言 信息操作处理
 * 
 * @author FJH
 * @date 2018-12-24
 */
@Controller
@RequestMapping("/system/secondcheck")
public class CommentSecondCheckController extends BaseController
{
    private String prefix = "system/comment";
	
	@Autowired
	private IActivityCommentService activityCommentService;
	@Autowired
	private IActivityService activityService;
	
	@RequiresPermissions("system:secondCheck:view")
	@GetMapping()
	public String activityComment()
	{
	    return prefix + "/secondcheck";
	}
	
	/**
	 * 查询留言列表
	 */
	@RequiresPermissions("system:secondCheck:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ActivityComment activityComment)
	{
		startPage();
        List<ActivityComment> list = activityCommentService.selectSecondCheckCommentList(activityComment);
        for (ActivityComment bean : list) {
        	bean.setComment(EmojiParser.parseToUnicode(bean.getComment()));
        }
		return getDataTable(list);
	}
	
	/**
	 * 查询活动
	 * @return
	 */
	@PostMapping("/queryActivity")
	@ResponseBody
	public AjaxResult queryActivityName(String userId)
	{
        List<Activity> list = activityService.selectActivityByUserId(Integer.parseInt(userId));
        AjaxResult json = new AjaxResult();
        json.put("code", 0);
		json.put("activity", JSON.toJSONString(list));
        return json;
	}
	
	/**
	 * 二级审核
	 */
	@Log(title = "二级级审核", businessType = BusinessType.UPDATE)
	@RequiresPermissions("system:secondCheck:check")
	@PostMapping( "/check")
	@ResponseBody
	public AjaxResult check(String id, String status)
	{		
		return toAjax(activityCommentService.check(id, status));
	}
	
}
