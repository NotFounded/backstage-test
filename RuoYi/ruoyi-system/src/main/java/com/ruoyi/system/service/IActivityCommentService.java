package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.ActivityComment;

/**
 * 留言 服务层
 * 
 * @author FJH
 * @date 2018-12-24
 */
public interface IActivityCommentService 
{
	/**
     * 查询留言信息
     * 
     * @param id 留言ID
     * @return 留言信息
     */
	public ActivityComment selectActivityCommentById(Integer id);
	
	/**
     * 查询留言列表
     * 
     * @param activityComment 留言信息
     * @return 留言集合
     */
	public List<ActivityComment> selectActivityCommentList(ActivityComment activityComment);
	
	/**
	 * 查询需要一级审核留言列表
	 * @param activityComment 留言信息
	 * @return
	 */
	public List<ActivityComment> selectFirstCheckCommentList(ActivityComment activityComment);

	/**
	 * 查询需要二级审核留言列表
	 * @param activityComment 留言信息
	 * @return
	 */
	public List<ActivityComment> selectSecondCheckCommentList(ActivityComment activityComment);

	/**
	 * 审核通过
	 * @param ids
	 * @param status
	 * @return
	 */
	public int check(String id, String status);
	
}
