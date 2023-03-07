package com.mkst.mini.systemplus.resful.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mkst.mini.systemplus.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Devdto {

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
}
