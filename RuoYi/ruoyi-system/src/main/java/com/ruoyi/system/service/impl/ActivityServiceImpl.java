package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.service.IActivityService;

/**
 * 活动 服务层实现
 * 
 * @author FJH
 * @date 2018-12-24
 */
@Service
public class ActivityServiceImpl implements IActivityService 
{
	@Autowired
	private ActivityMapper activityMapper;

	/**
     * 查询活动信息
     * 
     * @param id 活动ID
     * @return 活动信息
     */
    @Override
	public Activity selectActivityById(Integer id)
	{
	    return activityMapper.selectActivityById(id);
	}
	
	/**
     * 根据用户id查询活动列表
     * 
     * @param activity 活动信息
     * @return 活动集合
     */
	@Override
	public List<Activity> selectActivityByUserId(int userId)
	{
	    return activityMapper.selectActivityByUserId(userId);
	}
	
    /**
     * 新增活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	@Override
	public int insertActivity(Activity activity)
	{
	    return activityMapper.insertActivity(activity);
	}
	
	/**
     * 修改活动
     * 
     * @param activity 活动信息
     * @return 结果
     */
	@Override
	public int updateActivity(Activity activity)
	{
	    return activityMapper.updateActivity(activity);
	}

	/**
	 * 查询活动类型
	 */
	@Override
	public List<String> selectActivityType() {
		return activityMapper.selectActivityType();
	}

	/**
	 * 降活动安排至用户
	 */
	@Override
	public int addUserByActivityId(String userIds, String activityId) {
		int delResult = activityMapper.deleteUserActivityByActivityId(Integer.parseInt(activityId));
		if (delResult < 0) {
			return delResult;
		}
		if (StringUtils.isNotEmpty(userIds)) {
			Integer[] ids = Convert.toIntArray(userIds);
			for (int userId : ids) {
				int result = activityMapper.addUserByActivityId(userId, Integer.parseInt(activityId));
				if (result <= 0) {
					return -1;
				}
			}
		}
		return 1;
	}

	/**
	 * 查询活动列表
	 */
	@Override
	public List<Activity> selectActivityList(Activity activity) {
		return activityMapper.selectActivityList(activity);
	}

}
