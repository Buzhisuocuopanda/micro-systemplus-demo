package com.mkst.mini.systemplus.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mkst.mini.systemplus.common.base.BaseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 序列码表 sys_sequence
 * 
 * @author systemplus
 * @date 2019-03-01
 */
public class Sequence extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String name;
	/**  */
	private Integer currentValue;
	/**  */
	private Integer incrementValue;
	/** 键值类型.0：自增序列;15:20位UUID;225:长型UUID */
	private Integer uuidType;
	/** 长度类型.0：不限制长度;15:固定长度 */
	private Integer keyLenType;
	/** 序列键长度 */
	private Integer keyLen;
	/** 修饰类型.0:无修饰;15:前缀;255后缀 */
	private Integer keyFixType;
	/** 序列键修饰 */
	private String keyFix;
	/** 序列日期.0:非日期序列;15:日期序列 */
	private Integer keyDateType;
	/** 按日期循环增加.0:不循环;15:循环 */
	private Integer keyDateLoop;
	/** 循环初值 */
	private Integer keyDateLoopInit;
	/** 日期序列.0:yyyyMMdd;15:yyyyMM;255:yyyy */
	private Integer keyDateFormat;
	/** 上次序列开始时间 */
	private Date keyDate;

	
	private String example;//序列键示例
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}
	
	public Integer getIncrementValue() {
		return incrementValue;
	}

	public void setIncrementValue(Integer incrementValue) {
		this.incrementValue = incrementValue;
	}
	
	public Integer getUuidType() {
		return uuidType;
	}

	public void setUuidType(Integer uuidType) {
		this.uuidType = uuidType;
	}
	
	public Integer getKeyLenType() {
		return keyLenType;
	}

	public void setKeyLenType(Integer keyLenType) {
		this.keyLenType = keyLenType;
	}
	
	public Integer getKeyLen() {
		return keyLen;
	}

	public void setKeyLen(Integer keyLen) {
		this.keyLen = keyLen;
	}
	
	public Integer getKeyFixType() {
		return keyFixType;
	}

	public void setKeyFixType(Integer keyFixType) {
		this.keyFixType = keyFixType;
	}
	
	public String getKeyFix() {
		return keyFix;
	}

	public void setKeyFix(String keyFix) {
		this.keyFix = keyFix;
	}
	
	public Integer getKeyDateType() {
		return keyDateType;
	}

	public void setKeyDateType(Integer keyDateType) {
		this.keyDateType = keyDateType;
	}
	
	public Integer getKeyDateLoop() {
		return keyDateLoop;
	}

	public void setKeyDateLoop(Integer keyDateLoop) {
		this.keyDateLoop = keyDateLoop;
	}
	
	public Integer getKeyDateLoopInit() {
		return keyDateLoopInit;
	}

	public void setKeyDateLoopInit(Integer keyDateLoopInit) {
		this.keyDateLoopInit = keyDateLoopInit;
	}
	
	public Integer getKeyDateFormat() {
		return keyDateFormat;
	}

	public void setKeyDateFormat(Integer keyDateFormat) {
		this.keyDateFormat = keyDateFormat;
	}
	
	public Date getKeyDate() {
		return keyDate;
	}

	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}
	
	
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("currentValue", getCurrentValue())
            .append("incrementValue", getIncrementValue())
            .append("uuidType", getUuidType())
            .append("keyLenType", getKeyLenType())
            .append("keyLen", getKeyLen())
            .append("keyFixType", getKeyFixType())
            .append("keyFix", getKeyFix())
            .append("keyDateType", getKeyDateType())
            .append("keyDateLoop", getKeyDateLoop())
            .append("keyDateLoopInit", getKeyDateLoopInit())
            .append("keyDateFormat", getKeyDateFormat())
            .append("keyDate", getKeyDate())
            .toString();
    }
	
}
