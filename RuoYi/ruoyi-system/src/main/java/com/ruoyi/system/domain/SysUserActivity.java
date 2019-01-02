package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

/**
 * 用户活动关联表 
 * 
 * @author FJH
 * @date 2018-12-27
 */
public class SysUserActivity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private int user_id;
	
	private int activity_id;
	
    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("user_id", getUser_id())
            .append("activity_id", getActivity_id())
            .toString();
    }
}
