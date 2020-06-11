/**
 * @author FH
 * 2018年12月18日 下午5:00:57
 */
package com.mkst.mini.systemplus.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.mkst.mini.systemplus.common.utils.DateUtils;
import com.mkst.mini.systemplus.util.sm2.SM2Utils;
import com.mkst.mini.systemplus.util.sm2.Util;

/**
 * @author FH
 * 2018年12月18日 下午5:00:57 
 * 
 */
public class ApiUtils {
	

	public static Map<String, Object> getToken(String openId,String source) {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(StringUtils.isBlank(openId)){
				map.put("code", "2");
	    		map.put("message", "第三方用户ID为空");
	    		return map;
			}
			if(StringUtils.isBlank(source)){
				map.put("code", "2");
	    		map.put("message", "第三方数据来源为空");
	    		return map;
			}
			String token = openId+","+source+","+DateUtils.getTime();
			String objToken = SM2Utils.encrypt(Util.hexToByte(SysConfigUtil.getKey("sm2_public_key")), token.getBytes());
/*			SecretKey key = AESUtil.restoreSecretKey(AESUtil.IVPARAMETERS);
	        byte[] encodedText = AESUtil.AesEcbEncode(token.getBytes(), key);
			String objToken = Base64Utils.encodeToString(encodedText);*/
			map.put("code", "0");
    		map.put("message", "获取成功");
    		map.put("token", objToken);
    		return map;
		}catch(Exception e){
			map.put("code", "2");
    		map.put("message", "接口异常");
    		return map;
		}
	}
	
	public static Map<String,Object> validationToken(String token) {
		Map<String,Object> map =new HashMap<String,Object>();
		try {
/*			SecretKey key = AESUtil.restoreSecretKey(AESUtil.IVPARAMETERS);
			String objToken = AESUtil.AesEcbDecode(Base64Utils.decodeFromString(token), key);*/
			String objToken = new String(SM2Utils.decrypt(Util.hexToByte(SysConfigUtil.getKey("sm2_private_key")), Util.hexToByte(token)));
			String[] list = objToken.split(",");
			String openId  = list[0];
			String source  = list[1];
			String tokenTime = list[2];
			Date tokenDate = DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", tokenTime);
			int minutes = (int) DateUtils.pastMinutes(tokenDate);
			//设置token不过期
			/*if(minutes>120){
				map.put("code", "2");
				map.put("message", "token已超时");
				return map;
			}*/
			if(StringUtils.isNotBlank(openId)){
				map.put("openId", openId);
				map.put("source", source);
				map.put("code", "0");
				map.put("message", "验证成功");
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	  public static String byteToHex(byte[] src){
          StringBuilder stringBuilder = new StringBuilder("");
          if (src == null || src.length <= 0) {
              return null;
          }
          for (int i = 0; i < src.length; i++) {
              int v = src[i] & 0xFF;
              String hv = Integer.toHexString(v);
              if (hv.length() < 2) {
                  stringBuilder.append(0);
              }
              stringBuilder.append(hv);
          }
          return stringBuilder.toString();
      }
	  
	  public static String getIpAddr(HttpServletRequest request) {  
		    String ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    }  
		    return ip;  
		}  
	
}
