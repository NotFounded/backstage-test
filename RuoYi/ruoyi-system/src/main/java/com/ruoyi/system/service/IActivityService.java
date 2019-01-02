package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Activity;

/**
 * 活动 服务层
 * 
 * @author FJH
 * @date 2018-12-24
 */
public interface IActivityService 
{
	/**
     * 查询活动信息
     * 
     * @param id 活动ID
     * @return 活动信息
     */
	public Activity selectActivityById(Integer id);
	
	/**
     * 根据用户id查询活动列表
     * 
     * @param activity 活动信息
     * @return 活动集合
     */
	public List<Activity> selectActivityByUserId(int userId);
	
	/**
     * 新增活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	public int insertActivity(Activity activity);
	
	/**
     * 修改活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	public int updateActivity(Activity activity);
		
	/**
	 * 查询活动类型
	 * @return
	 */
	public List<String> selectActivityType();

	/**
	 * 将活动安排至用户
	 * @param userIds
	 * @param activityId
	 * @return
	 */
	public int addUserByActivityId(String userIds, String activityId);

	/**
	 * 查询活动列表
	 * @param activity
	 * @return
	 */
	public List<Activity> selectActivityList(Activity activity);

}
