/**
 * @author FH
 * 2019年3月13日 下午5:31:56
 */
package com.mkst.mini.systemplus.common.util;

import org.apache.commons.lang3.StringUtils;

import com.mkst.mini.systemplus.common.service.SequenceService;
import com.mkst.mini.systemplus.framework.util.SpringUtils;

/**
 * @author FH
 * 2019年3月13日 下午5:31:56 
 * 
 */
public class SequenceUtil {
	
	private static SequenceService SequenceService  =   SpringUtils.getBean(SequenceService.class);
	
	public static String getKey(String keyName){
		if(StringUtils.isEmpty(keyName)){
			return null;
		}else{
			return SequenceService.getKey(keyName);
		}
	}

}
