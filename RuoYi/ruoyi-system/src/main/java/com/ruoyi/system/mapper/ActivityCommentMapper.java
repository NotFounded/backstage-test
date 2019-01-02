package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.system.domain.ActivityComment;	

/**
 * 留言 数据层
 * 
 * @author FJH
 * @date 2018-12-24
 */
public interface ActivityCommentMapper 
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
	 * 查询一级审核所需留言列表
	 * @param activityComment
	 * @return
	 */
	public List<ActivityComment> selectFirstCheckCommentList(ActivityComment activityComment);

	/**
	 * 查询需要二级审核的留言
	 * @param activityComment
	 * @return
	 */
	public List<ActivityComment> selectSecondCheckCommentList(ActivityComment activityComment);

	/**
	 * 审核
	 * @param id
	 * @param status
	 * @return
	 */
	public int check(@Param("id")String id, @Param("status")String status);
	

}