package com.mkst.mini.systemplus.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mkst.mini.systemplus.common.base.BaseEntity;
import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.util.QRCode;

/**
 * 通知公告表 sys_notice
 * 
 * @author admin
 */
public class SysNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Long noticeId;
    
    /** 公告标题 */
    private String noticeTitle;

    /** 公告图片 */
    private String noticeImage;
    
    /** 公告类型（1通知 2公告） */
    private String noticeType;
    
    /** 公告内容 */
    private String noticeContent;
    
    /** 公告状态（0正常 1关闭） */
    private String status;
    
    private String qrcodeUrl;

    public Long getNoticeId()
    {
        return noticeId;
    }

    public void setNoticeId(Long noticeId)
    {
        this.noticeId = noticeId;
    }

    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle()
    {
        return noticeTitle;
    }

    public String getNoticeImage() {
        return noticeImage;
    }

    public void setNoticeImage(String noticeImage) {
        this.noticeImage = noticeImage;
    }

    public void setNoticeType(String noticeType)
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType()
    {
        return noticeType;
    }

    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    
    public String getQrcodeUrl() {
    	if(noticeId != null && noticeId != 0) {
    		try {
				return QRCode.decode(Global.getProfile()+noticeId+".png");
			} catch (Exception e) {
			}
    	}
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("noticeTitle", getNoticeTitle())
            .append("noticeImage", getNoticeImage())
            .append("noticeType", getNoticeType())
            .append("noticeContent", getNoticeContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
