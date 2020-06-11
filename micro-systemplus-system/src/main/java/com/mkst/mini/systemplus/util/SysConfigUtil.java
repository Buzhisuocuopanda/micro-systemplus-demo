package com.mkst.mini.systemplus.util;

import com.mkst.mini.systemplus.framework.util.SpringUtils;
import com.mkst.mini.systemplus.system.service.ISysConfigService;

public class SysConfigUtil {
	private static ISysConfigService configService;

	public static ISysConfigService getConfigService() {
		if (configService == null) {
			configService = SpringUtils.getBean(ISysConfigService.class);
		}
		return configService;
	} 
			
	public static String getKey(String key) {
		return getConfigService().selectConfigByKey(key);
	}
}
