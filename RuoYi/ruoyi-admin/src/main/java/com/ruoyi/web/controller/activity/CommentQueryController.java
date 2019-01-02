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
import com.ruoyi.common.base.AjaxResult;
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
@RequestMapping("/system/activityComment")
public class CommentQueryController extends BaseController
{
    private String prefix = "system/comment";
	
	@Autowired
	private IActivityCommentService activityCommentService;
	@Autowired
	private IActivityService activityService;
	
	@RequiresPermissions("system:activityComment:view")
	@GetMapping()
	public String activityComment()
	{
	    return prefix + "/activityComment";
	}
	
	/**
	 * 查询留言列表
	 */
	@RequiresPermissions("system:activityComment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ActivityComment activityComment)
	{
		startPage();
        List<ActivityComment> list = activityCommentService.selectActivityCommentList(activityComment);
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
	
}
