package com.ruoyi.system.service;

import com.ruoyi.system.domain.ActivityConfig;
import java.util.List;

/**
 * 活动配置 服务层
 * 
 * @author FJH
 * @date 2018-12-26
 */
public interface IActivityConfigService 
{
	/**
     * 查询活动配置信息
     * 
     * @param id 活动配置ID
     * @return 活动配置信息
     */
	public ActivityConfig selectActivityConfigById(Integer id);
	
	/**
     * 查询活动配置列表
     * 
     * @param activityConfig 活动配置信息
     * @return 活动配置集合
     */
	public List<ActivityConfig> selectActivityConfigList(ActivityConfig activityConfig);
	
	/**
     * 新增活动配置
     * 
     * @param activityConfig 活动配置信息
     * @return 结果
     */
	public int insertActivityConfig(ActivityConfig activityConfig);
	
	/**
     * 修改活动配置
     * 
     * @param activityConfig 活动配置信息
     * @return 结果
     */
	public int updateActivityConfig(ActivityConfig activityConfig);
		
	/**
	 * 根据活动Id查询活动配置
	 * @param id
	 * @return
	 */
	public List<ActivityConfig> selectConfigByActivityId(Integer activityId);
	
}
