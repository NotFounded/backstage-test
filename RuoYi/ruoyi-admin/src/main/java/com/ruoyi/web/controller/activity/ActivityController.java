package com.ruoyi.web.controller.activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.ActivityConfig;
import com.ruoyi.system.service.IActivityConfigService;
import com.ruoyi.system.service.IActivityService;

/**
 * 活动 信息操作处理
 * 
 * @author FJH
 * @date 2018-12-24
 */
@Controller
@RequestMapping("/system/activity")
public class ActivityController extends BaseController
{
    private String prefix = "system/activity";
	
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IActivityConfigService activityConfigService;
	
	@RequiresPermissions("system:activity:view")
	@GetMapping()
	public String activity()
	{
	    return prefix + "/activity";
	}
	
	/**
	 * 查询活动列表
	 */
	@RequiresPermissions("system:activity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Activity activity)
	{
        List<Activity> list = activityService.selectActivityList(activity);
		return getDataTable(list);
	}
	
	
	@PostMapping("/queryActivityType")
	@ResponseBody
	public AjaxResult queryActivityType()
	{
		AjaxResult json = new AjaxResult();
        List<String> list = activityService.selectActivityType();
        json.put("code", 0);
		json.put("types", list);
		return json;
	}
	
	/**
	 * 新增活动
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存活动
	 */
	@RequiresPermissions("system:activity:add")
	@Log(title = "活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HttpServletRequest request)
	{		
		//活动表
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String appKey = request.getParameter("appKey");
		String appSecret = request.getParameter("appSecret");
		String enable = request.getParameter("enable");
		if (StringUtils.isEmpty(description) || StringUtils.isEmpty(type) || StringUtils.isEmpty(appKey)
				|| StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(enable)) {
			return AjaxResult.error("活动信息不能为空");
		}
		String limitWords = request.getParameter("limitWords");
		String checkLevel = request.getParameter("checkLevel");
		String nameRule = request.getParameter("nameRule");
		String pwdLengthRule = request.getParameter("pwdLengthRule");
		String reponseCounts = request.getParameter("reponseCounts");
		String pageCacheCounts = request.getParameter("pageCacheCounts");
		if (StringUtils.equals(type, "comment") && (StringUtils.isEmpty(limitWords) || StringUtils.isEmpty(checkLevel) || StringUtils.isEmpty(nameRule)
				|| StringUtils.isEmpty(pwdLengthRule) || StringUtils.isEmpty(reponseCounts) || StringUtils.isEmpty(pageCacheCounts))) {
			return AjaxResult.error("活动配置不能为空");
		}
		Activity activity = new Activity();
		activity.setDescription(description);
		activity.setType(type);
		activity.setAppKey(appKey);
		activity.setAppSecret(appSecret);
		activity.setTime(System.currentTimeMillis());
		activity.setEnable(Integer.parseInt(enable));
		int result = activityService.insertActivity(activity);
		if (result <= 0) {
			return AjaxResult.error("保存失败");
		}
		int activityId = activity.getId();
		int limitWordsResult = insertConfig(activityId, "limit_words", limitWords);
		int checkLevelResult = insertConfig(activityId, "check_level", checkLevel);
		int nameRuleResult = insertConfig(activityId, "name_rule", nameRule);
		int pwdLengthRuleResult = insertConfig(activityId, "pwd_length_rule", pwdLengthRule);
		int reponseCountsResult = insertConfig(activityId, "reponse_counts", reponseCounts);
		int pageCacheCountsResult = insertConfig(activityId, "page_cache_counts", pageCacheCounts);
		if (limitWordsResult <= 0 || checkLevelResult <=0 || nameRuleResult <= 0 || pwdLengthRuleResult <= 0 || reponseCountsResult <= 0 || pageCacheCountsResult <= 0) {
			return AjaxResult.error("保存失败！");
		}
		return AjaxResult.success();
	}

	/**
	 * 插入活动配置数据
	 * @param activityId
	 * @param key
	 * @param value
	 * @return
	 */
	private int insertConfig(int activityId, String key, String value) {
		ActivityConfig activityConfig = new ActivityConfig();
		activityConfig.setActivity_id(activityId);
		activityConfig.setKey(key);
		activityConfig.setValue(value);
		return activityConfigService.insertActivityConfig(activityConfig);
	}

	/**
	 * 修改活动
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Activity activity = activityService.selectActivityById(id);
		List<ActivityConfig> configList = activityConfigService.selectConfigByActivityId(id);
		List<String> activityTypes = activityService.selectActivityType();
		mmap.put("activity", activity);
		mmap.put("configList", configList);
		mmap.put("activityTypes", activityTypes);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存活动
	 */
	@RequiresPermissions("system:activity:edit")
	@Log(title = "活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HttpServletRequest request)
	{		
		String id = request.getParameter("id");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String appKey = request.getParameter("appKey");
		String appSecret = request.getParameter("appSecret");
		String enable = request.getParameter("enable");
		if (StringUtils.isEmpty(description) || StringUtils.isEmpty(type) || StringUtils.isEmpty(appKey)
				|| StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(enable)) {
			return AjaxResult.error("活动信息不能为空");
		}
		String limitWords = request.getParameter("limitWords");
		String checkLevel = request.getParameter("checkLevel");
		String nameRule = request.getParameter("nameRule");
		String pwdLengthRule = request.getParameter("pwdLengthRule");
		String pageCacheCounts = request.getParameter("pageCacheCounts");
		String reponseCounts = request.getParameter("reponseCounts");
		if (StringUtils.equals(type, "comment") && (StringUtils.isEmpty(limitWords) || StringUtils.isEmpty(checkLevel) || StringUtils.isEmpty(nameRule)
				|| StringUtils.isEmpty(pwdLengthRule) || StringUtils.isEmpty(reponseCounts) || StringUtils.isEmpty(pageCacheCounts))) {
			return AjaxResult.error("活动配置不能为空");
		}
		Activity activity = new Activity();
		activity.setId(Integer.parseInt(id));
		activity.setDescription(description);
		activity.setType(type);
		activity.setAppKey(appKey);
		activity.setAppSecret(appSecret);
		activity.setTime(System.currentTimeMillis());
		activity.setEnable(Integer.parseInt(enable));
		int result = activityService.updateActivity(activity);
		if (result <= 0) {
			return AjaxResult.error("保存失败");
		}
		int limitWordsResult = updateConfig(Integer.parseInt(id), "limit_words", limitWords);
		int checkLevelResult = updateConfig(Integer.parseInt(id), "check_level", checkLevel);
		int nameRuleResult = updateConfig(Integer.parseInt(id), "name_rule", nameRule);
		int pwdLengthRuleResult = updateConfig(Integer.parseInt(id), "pwd_length_rule", pwdLengthRule);
		int reponseCountsResult = updateConfig(Integer.parseInt(id), "reponse_counts", reponseCounts);
		int pageCacheCountsResult = updateConfig(Integer.parseInt(id), "page_cache_counts", pageCacheCounts);
		
		if (limitWordsResult <= 0 || checkLevelResult <=0 || nameRuleResult <= 0 || pwdLengthRuleResult <= 0 || reponseCountsResult <= 0 || pageCacheCountsResult <= 0) {
			return AjaxResult.error("保存失败！");
		}
		
		return AjaxResult.success();
	}

	/**
	 * 更新活动配置
	 * @param id
	 * @param string
	 * @param limitWords
	 * @return
	 */
	private int updateConfig(int activityId, String key, String value) {
		ActivityConfig activityConfig = new ActivityConfig();
		activityConfig.setActivity_id(activityId);
		activityConfig.setKey(key);
		activityConfig.setValue(value);
		return activityConfigService.updateActivityConfig(activityConfig);
	}
	
	/**
     * 用户管理
     */
    @GetMapping("/addUser/{activityId}")
    public String rule(@PathVariable("activityId") Integer activityId, ModelMap mmap)
    {
    	mmap.put("activityId", activityId);
        return prefix + "/addUser";
    }
	
    /**
     * 给活动安排用户
     * @param userIds
     * @param activityId
     * @return
     */
    @Log(title = "活动相关用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:activity:addUser")
    @PostMapping("/addUser")
	@ResponseBody
    public AjaxResult addUser(String userIds, String activityId) {
    	return toAjax(activityService.addUserByActivityId(userIds, activityId));
    }
    
}
