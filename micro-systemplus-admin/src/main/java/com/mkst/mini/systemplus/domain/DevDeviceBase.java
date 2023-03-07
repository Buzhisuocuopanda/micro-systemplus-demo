package com.mkst.mini.systemplus.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mkst.mini.systemplus.common.annotation.Excel;
import com.mkst.mini.systemplus.common.base.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 设备基本信息对象 dev_device_base
 * 
 * @author ruoyi
 * @date 2023-03-06
 */
@Data
public class DevDeviceBase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备序号 */
    private Long id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String devNo;

    /** 设备类型，0设备网关，1物联设备，2监控设备 */
    @Excel(name = "设备类型，0设备网关，1物联设备，2监控设备")
    private String devType;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String devName;
    private String deptName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specModel;

    /** 制造单位 */
    @Excel(name = "制造单位")
    private String devFactory;

    /** 设备数量 */
    @Excel(name = "设备数量")
    private BigDecimal devNumber;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String groupName;

    /** 购入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 保修期限 */
    @Excel(name = "保修期限")
    private String devGuaranteeTime;

    /** 使用部门 */
    @Excel(name = "使用部门")
    private String deptId;

    /** 管理人员 */
    @Excel(name = "管理人员")
    private String manageuser;

    /** 设备价格 */
    @Excel(name = "设备价格")
    private BigDecimal devValue;

    /** 安装地点 */
    @Excel(name = "安装地点")
    private String devLocal;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Long devStatus;

    /** 删除标记 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDevNo(String devNo) 
    {
        this.devNo = devNo;
    }

    public String getDevNo() 
    {
        return devNo;
    }
    public void setDevType(String devType) 
    {
        this.devType = devType;
    }

    public String getDevType() 
    {
        return devType;
    }
    public void setDevName(String devName) 
    {
        this.devName = devName;
    }

    public String getDevName() 
    {
        return devName;
    }
    public void setSpecModel(String specModel) 
    {
        this.specModel = specModel;
    }

    public String getSpecModel() 
    {
        return specModel;
    }
    public void setDevFactory(String devFactory) 
    {
        this.devFactory = devFactory;
    }

    public String getDevFactory() 
    {
        return devFactory;
    }
    public void setDevNumber(BigDecimal devNumber) 
    {
        this.devNumber = devNumber;
    }

    public BigDecimal getDevNumber() 
    {
        return devNumber;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setPurchaseDate(Date purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() 
    {
        return purchaseDate;
    }
    public void setDevGuaranteeTime(String devGuaranteeTime) 
    {
        this.devGuaranteeTime = devGuaranteeTime;
    }

    public String getDevGuaranteeTime() 
    {
        return devGuaranteeTime;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setManageuser(String manageuser) 
    {
        this.manageuser = manageuser;
    }

    public String getManageuser() 
    {
        return manageuser;
    }
    public void setDevValue(BigDecimal devValue) 
    {
        this.devValue = devValue;
    }

    public BigDecimal getDevValue() 
    {
        return devValue;
    }
    public void setDevLocal(String devLocal) 
    {
        this.devLocal = devLocal;
    }

    public String getDevLocal() 
    {
        return devLocal;
    }
    public void setDevStatus(Long devStatus) 
    {
        this.devStatus = devStatus;
    }

    public Long getDevStatus() 
    {
        return devStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("devNo", getDevNo())
            .append("devType", getDevType())
            .append("devName", getDevName())
            .append("specModel", getSpecModel())
            .append("devFactory", getDevFactory())
            .append("devNumber", getDevNumber())
            .append("groupName", getGroupName())
            .append("purchaseDate", getPurchaseDate())
            .append("devGuaranteeTime", getDevGuaranteeTime())
            .append("deptId", getDeptId())
            .append("manageuser", getManageuser())
            .append("devValue", getDevValue())
            .append("devLocal", getDevLocal())
            .append("createBy", getCreateBy())
            .append("devStatus", getDevStatus())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
