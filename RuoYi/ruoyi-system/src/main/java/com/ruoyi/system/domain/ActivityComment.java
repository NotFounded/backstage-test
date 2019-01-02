package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

/**
 * 留言表 theme_activity_comment
 * 
 * @author FJH
 * @date 2018-12-24
 */
public class ActivityComment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer userId;
	/**  */
	private Integer activityId;
	/**  */
	private String username;
	/**  */
	private String comment;
	/**  */
	private Integer praiseCounts;
	
	private Activity activity;
	/**  */
	private Long time;
	/** 通过状态，0：未通过，1：初级审核通过，2：高级审核通过，100：未通过 */
	private Integer status;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setActivityId(Integer activityId) 
	{
		this.activityId = activityId;
	}

	public Integer getActivityId() 
	{
		return activityId;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment() 
	{
		return comment;
	}
	public void setPraiseCounts(Integer praiseCounts) 
	{
		this.praiseCounts = praiseCounts;
	}

	public Integer getPraiseCounts() 
	{
		return praiseCounts;
	}
	public void setTime(Long time) 
	{
		this.time = time;
	}

	public Long getTime() 
	{
		return time;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

    public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("activityId", getActivityId())
            .append("username", getUsername())
            .append("comment", getComment())
            .append("praiseCounts", getPraiseCounts())
            .append("time", getTime())
            .append("status", getStatus())
            .toString();
    }
}
