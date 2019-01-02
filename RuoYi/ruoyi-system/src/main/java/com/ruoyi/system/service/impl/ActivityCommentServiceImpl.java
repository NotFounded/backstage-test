package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.ActivityComment;
import com.ruoyi.system.mapper.ActivityCommentMapper;
import com.ruoyi.system.service.IActivityCommentService;

/**
 * 留言 服务层实现
 * 
 * @author FJH
 * @date 2018-12-24
 */
@Service
public class ActivityCommentServiceImpl implements IActivityCommentService 
{
	@Autowired
	private ActivityCommentMapper activityCommentMapper;

	/**
     * 查询留言信息
     * 
     * @param id 留言ID
     * @return 留言信息
     */
    @Override
	public ActivityComment selectActivityCommentById(Integer id)
	{
	    return activityCommentMapper.selectActivityCommentById(id);
	}
	
	/**
     * 查询留言列表
     * 
     * @param activityComment 留言信息
     * @return 留言集合
     */
	@Override
	public List<ActivityComment> selectActivityCommentList(ActivityComment activityComment)
	{
	    return activityCommentMapper.selectActivityCommentList(activityComment);
	}
	
	/**
	 * 查询一级审核所需留言列表
	 */
	@Override
	public List<ActivityComment> selectFirstCheckCommentList(ActivityComment activityComment) {
		 return activityCommentMapper.selectFirstCheckCommentList(activityComment);
	}

	/**
	 * 查询需要二级审核的留言
	 */
	@Override
	public List<ActivityComment> selectSecondCheckCommentList(ActivityComment activityComment) {
		return activityCommentMapper.selectSecondCheckCommentList(activityComment);
	}

	/**
	 * 审核通过
	 */
	@Override
	public int check(String id, String status) {
		return activityCommentMapper.check(id, status);
	}

}
