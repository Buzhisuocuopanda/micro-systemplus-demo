package com.mkst.mini.systemplus.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DevDeviceBase implements Serializable {
    private Long id;

    private String devNo;

    private String devType;

    private String devName;

    private String specModel;

    private String devFactory;

    private BigDecimal devNumber;

    private String groupName;

    private Date purchaseDate;

    private String devGuaranteeTime;

    private String deptId;

    private String manageuser;

    private BigDecimal devValue;

    private String devLocal;

    private String createBy;

    private Integer devStatus;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String delFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo == null ? null : devNo.trim();
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType == null ? null : devType.trim();
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }

    public String getSpecModel() {
        return specModel;
    }

    public void setSpecModel(String specModel) {
        this.specModel = specModel == null ? null : specModel.trim();
    }

    public String getDevFactory() {
        return devFactory;
    }

    public void setDevFactory(String devFactory) {
        this.devFactory = devFactory == null ? null : devFactory.trim();
    }

    public BigDecimal getDevNumber() {
        return devNumber;
    }

    public void setDevNumber(BigDecimal devNumber) {
        this.devNumber = devNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getDevGuaranteeTime() {
        return devGuaranteeTime;
    }

    public void setDevGuaranteeTime(String devGuaranteeTime) {
        this.devGuaranteeTime = devGuaranteeTime == null ? null : devGuaranteeTime.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getManageuser() {
        return manageuser;
    }

    public void setManageuser(String manageuser) {
        this.manageuser = manageuser == null ? null : manageuser.trim();
    }

    public BigDecimal getDevValue() {
        return devValue;
    }

    public void setDevValue(BigDecimal devValue) {
        this.devValue = devValue;
    }

    public String getDevLocal() {
        return devLocal;
    }

    public void setDevLocal(String devLocal) {
        this.devLocal = devLocal == null ? null : devLocal.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DevDeviceBase other = (DevDeviceBase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDevNo() == null ? other.getDevNo() == null : this.getDevNo().equals(other.getDevNo()))
            && (this.getDevType() == null ? other.getDevType() == null : this.getDevType().equals(other.getDevType()))
            && (this.getDevName() == null ? other.getDevName() == null : this.getDevName().equals(other.getDevName()))
            && (this.getSpecModel() == null ? other.getSpecModel() == null : this.getSpecModel().equals(other.getSpecModel()))
            && (this.getDevFactory() == null ? other.getDevFactory() == null : this.getDevFactory().equals(other.getDevFactory()))
            && (this.getDevNumber() == null ? other.getDevNumber() == null : this.getDevNumber().equals(other.getDevNumber()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getPurchaseDate() == null ? other.getPurchaseDate() == null : this.getPurchaseDate().equals(other.getPurchaseDate()))
            && (this.getDevGuaranteeTime() == null ? other.getDevGuaranteeTime() == null : this.getDevGuaranteeTime().equals(other.getDevGuaranteeTime()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getManageuser() == null ? other.getManageuser() == null : this.getManageuser().equals(other.getManageuser()))
            && (this.getDevValue() == null ? other.getDevValue() == null : this.getDevValue().equals(other.getDevValue()))
            && (this.getDevLocal() == null ? other.getDevLocal() == null : this.getDevLocal().equals(other.getDevLocal()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getDevStatus() == null ? other.getDevStatus() == null : this.getDevStatus().equals(other.getDevStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDevNo() == null) ? 0 : getDevNo().hashCode());
        result = prime * result + ((getDevType() == null) ? 0 : getDevType().hashCode());
        result = prime * result + ((getDevName() == null) ? 0 : getDevName().hashCode());
        result = prime * result + ((getSpecModel() == null) ? 0 : getSpecModel().hashCode());
        result = prime * result + ((getDevFactory() == null) ? 0 : getDevFactory().hashCode());
        result = prime * result + ((getDevNumber() == null) ? 0 : getDevNumber().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getPurchaseDate() == null) ? 0 : getPurchaseDate().hashCode());
        result = prime * result + ((getDevGuaranteeTime() == null) ? 0 : getDevGuaranteeTime().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getManageuser() == null) ? 0 : getManageuser().hashCode());
        result = prime * result + ((getDevValue() == null) ? 0 : getDevValue().hashCode());
        result = prime * result + ((getDevLocal() == null) ? 0 : getDevLocal().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getDevStatus() == null) ? 0 : getDevStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", devNo=").append(devNo);
        sb.append(", devType=").append(devType);
        sb.append(", devName=").append(devName);
        sb.append(", specModel=").append(specModel);
        sb.append(", devFactory=").append(devFactory);
        sb.append(", devNumber=").append(devNumber);
        sb.append(", groupName=").append(groupName);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", devGuaranteeTime=").append(devGuaranteeTime);
        sb.append(", deptId=").append(deptId);
        sb.append(", manageuser=").append(manageuser);
        sb.append(", devValue=").append(devValue);
        sb.append(", devLocal=").append(devLocal);
        sb.append(", createBy=").append(createBy);
        sb.append(", devStatus=").append(devStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
