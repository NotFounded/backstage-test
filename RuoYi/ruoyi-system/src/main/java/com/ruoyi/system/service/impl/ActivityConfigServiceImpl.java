package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.ActivityConfig;
import com.ruoyi.system.mapper.ActivityConfigMapper;
import com.ruoyi.system.service.IActivityConfigService;

/**
 * 活动配置 服务层实现
 * 
 * @author FJH
 * @date 2018-12-26
 */
@Service
public class ActivityConfigServiceImpl implements IActivityConfigService 
{
	@Autowired
	private ActivityConfigMapper activityConfigMapper;

	/**
     * 查询活动配置信息
     * 
     * @param id 活动配置ID
     * @return 活动配置信息
     */
    @Override
	public ActivityConfig selectActivityConfigById(Integer id)
	{
	    return activityConfigMapper.selectActivityConfigById(id);
	}
	
	/**
     * 查询活动配置列表
     * 
     * @param activityConfig 活动配置信息
     * @return 活动配置集合
     */
	@Override
	public List<ActivityConfig> selectActivityConfigList(ActivityConfig activityConfig)
	{
	    return activityConfigMapper.selectActivityConfigList(activityConfig);
	}
	
    /**
     * 新增活动配置
     * 
     * @param activityConfig 活动配置信息
     * @return 结果
     */
	@Override
	public int insertActivityConfig(ActivityConfig activityConfig)
	{
	    return activityConfigMapper.insertActivityConfig(activityConfig);
	}
	
	/**
     * 修改活动配置
     * 
     * @param activityConfig 活动配置信息
     * @return 结果
     */
	@Override
	public int updateActivityConfig(ActivityConfig activityConfig)
	{
	    return activityConfigMapper.updateActivityConfig(activityConfig);
	}

	/**
	 * 根据活动Id查询活动配置
	 */
	@Override
	public List<ActivityConfig> selectConfigByActivityId(Integer activityId) {
		return activityConfigMapper.selectConfigByActivityId(activityId);
	}
	
}
