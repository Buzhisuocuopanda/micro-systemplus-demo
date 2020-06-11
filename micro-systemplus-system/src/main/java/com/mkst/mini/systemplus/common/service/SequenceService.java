/**
 * @author FH
 * 2019年3月1日 上午10:02:47
 */
package com.mkst.mini.systemplus.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkst.mini.systemplus.system.service.ISequenceService;

/**
 * @author FH
 * 2019年3月1日 上午10:02:47 
 * 
 */
@Service("sequence")
public class SequenceService {
		
	@Autowired
	private ISequenceService sequenceService;
	
	public String getKey(String keyName) {
		return sequenceService.getKey(keyName);
	}
	
}
