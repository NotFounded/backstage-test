package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

/**
 * 活动表 theme_activity
 * 
 * @author FJH
 * @date 2018-12-24
 */
public class Activity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String description;
	/**  */
	private String type;
	/**  */
	private String appKey;
	/**  */
	private String appSecret;
	/**  */
	private Long time;
	/**  */
	private Integer enable;
	
	private SysUserActivity sysUserActivity;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setAppKey(String appKey) 
	{
		this.appKey = appKey;
	}

	public String getAppKey() 
	{
		return appKey;
	}
	public void setAppSecret(String appSecret) 
	{
		this.appSecret = appSecret;
	}

	public String getAppSecret() 
	{
		return appSecret;
	}
	public void setTime(Long time) 
	{
		this.time = time;
	}

	public Long getTime() 
	{
		return time;
	}
	public void setEnable(Integer enable) 
	{
		this.enable = enable;
	}

	public Integer getEnable() 
	{
		return enable;
	}

    public SysUserActivity getSysUserActivity() {
		return sysUserActivity;
	}

	public void setSysUserActivity(SysUserActivity sysUserActivity) {
		this.sysUserActivity = sysUserActivity;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("description", getDescription())
            .append("type", getType())
            .append("appKey", getAppKey())
            .append("appSecret", getAppSecret())
            .append("time", getTime())
            .append("enable", getEnable())
            .toString();
    }
}
