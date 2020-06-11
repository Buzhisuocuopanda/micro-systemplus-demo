/**
 * @author FH
 * 2018年12月18日 下午5:00:57
 */
package com.mkst.mini.systemplus.common.util;

import com.mkst.mini.systemplus.common.service.DictService;
import com.mkst.mini.systemplus.framework.util.SpringUtils;
import com.mkst.mini.systemplus.system.domain.SysDictData;

import java.util.List;

/**
 * @author FH
 * 2018年12月18日 下午5:00:57 
 * 
 */
public class DictUtil {
	
	private static DictService dictService  =   SpringUtils.getBean(DictService.class);
	
	public static String getDictLabel(String type, String value){
		return dictService.getLabel(type, value);
	}
	
	public static String getDictValue(String type, String label){
		return dictService.getValue(type, label);
	}
	
	public static  List<SysDictData> getType(String type){
		return dictService.getType(type);
	}
}
