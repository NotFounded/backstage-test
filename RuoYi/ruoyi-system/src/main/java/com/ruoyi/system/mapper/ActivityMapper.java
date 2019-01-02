package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.Activity;	

/**
 * 活动 数据层
 * 
 * @author FJH
 * @date 2018-12-24
 */
public interface ActivityMapper 
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
	 * 降活动安排到用户
	 * @param userId
	 * @param parseInt
	 * @return
	 */
	public int addUserByActivityId(@Param("userId")int userId, @Param("activityId")int activityId);

	/**
	 * 根据活动Id删除关联用户
	 * @param parseInt
	 * @return
	 */
	public int deleteUserActivityByActivityId(int activityId);

	/**
	 * 查询活动列表
	 * @param activity
	 * @return
	 */
	public List<Activity> selectActivityList(Activity activity);

}