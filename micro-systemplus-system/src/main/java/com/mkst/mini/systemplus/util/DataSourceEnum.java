/**
 * 
 */
package com.mkst.mini.systemplus.util;

/**
 * @author zhoufangyuan
 * 第三方系统来源
 * 2018年7月19日 下午3:21:32
 */
public enum DataSourceEnum {
	WE_CHAT("微信","0"),//微信
	ALI_PAY("支付宝","1"),//支付宝
	H5("H5","2"),//H5
	APP("APP","3");//APP
	DataSourceEnum(String name,String value) {
		this.name = name;
		this.value = value;
	}

	private String name;
	private String value;
	
	// 普通方法
	public static String getName(String value) {
		for (DataSourceEnum c : DataSourceEnum.values()) {
			if (c.getValue().equals(value)) {
				return c.name;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
