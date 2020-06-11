//package com.mkst.mini.systemplus.common.redis;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import com.mkst.mini.systemplus.common.config.Global;
//import com.mkst.mini.systemplus.common.utils.Md5Utils;
//
///**
// * redicache 工具类
// * 在application.yml文件内的Spring-redis的run配置启动,true为启动;false为不启动;
// *
// * @author PanMeiCheng
// * @date 2018年12月10日 23:05:39
// * @company
// */
//@SuppressWarnings("unchecked")
//@Component
//public class RedisUtils {
//    /**
//     * 日志对象
//     */
//    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
//
//    /**
//     * The constant SHIRO_REDIS.
//     */
//    public static String SHIRO_REDIS="shiro_redis_cache";
//    /**
//     * The constant SHIRO_REDIS_OBJECT.
//     */
//    public static String SHIRO_REDIS_OBJECT="org.apache.shiro.subject.SimplePrincipalCollection";
//    /**
//     * The constant GET_ID.
//     */
//    public static String GET_ID =".get id=";
//    /**
//     * The constant TOTAL_KEY.
//     */
//    public static String TOTAL_KEY =".total key=";
//    /**
//     * The constant FIND_LIST_KEY.
//     */
//    public static String FIND_LIST_KEY =".findList key=";
//    /**
//     * The constant FIND_LIST_FIRST_KEY.
//     */
//    public static String FIND_LIST_FIRST_KEY =".findListFirst key=";
//    /**
//     * The constant FIND_PAGE_KEY.
//     */
//    public static String FIND_PAGE_KEY =".findPage key=";
//    /**
//     * The constant FIND_LIST_KEY_PATTERN.
//     */
//    public static String FIND_LIST_KEY_PATTERN =".findList key=*";
//    /**
//     * The constant FIND_LIST_FIRST_KEY_PATTERN.
//     */
//    public static String FIND_LIST_FIRST_KEY_PATTERN =".findListFirst key=*";
//    /**
//     * The constant FIND_PAGE_KEY_PATTERN.
//     */
//    public static String FIND_PAGE_KEY_PATTERN =".findPage key=*";
//    /**
//     * The constant KEY_PREFIX.
//     */
//    public static String KEY_PREFIX = Global.getConfig("spring.redis.keyPrefix");
//    /**
//     * The constant expireTime.
//     */
//    public static Long expireTime= Long.parseLong(Global.getConfig("spring.redis.expireTime"));
//    /**
//     * The constant expireTimeShiro.
//     */
//    public static Long expireTimeShiro= Long.parseLong(Global.getConfig("spring.redis.expireTimeShiro"));
//    /**
//     * The constant RUN_MESSAGE.
//     */
//    public static String RUN_MESSAGE="请开启Redis服务,还有Redis密码配置是否有误，而且密码不能为空（为空时Redis服务会连接不上）。";
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * Get spring.redis.expireTime 配置时间.
//     *
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getExpire(){
//        if(expireTime!=null) {
//            return String.valueOf(expireTime / 60) + "分钟";
//        } else {
//            return "0分钟";
//        }
//    }
//
//    /**
//     * Get spring.redis.expireTimeShiro 配置时间.
//     *
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getExpireShiro(){
//        if(expireTimeShiro!=null) {
//            return String.valueOf(expireTimeShiro / 60) + "分钟";
//        } else {
//            return "0分钟";
//        }
//    }
//
//    /**
//     * Get 拼接key KEY_PREFIX+className+keyName+keyId.
//     * @param className the class name
//     * @param keyName   the key name
//     * @param keyId     the key id
//     * @return  KEY_PREFIX+className+keyName+keyId
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getKey(String className,String keyName,String keyId){
//        if(className==null){className="";}
//        if(keyId==null){keyId="";}
//        return RedisUtils.KEY_PREFIX+className+keyName+keyId;
//    }
//
//    /**
//     * Get id key string.<br>
//     *KEY_PREFIX+className+GET_ID+keyId
//     * @param className the class name
//     * @param keyId     the key id
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getIdKey(String className,String keyId){
//        return getKey(className,RedisUtils.GET_ID,keyId);
//    }
//
//    /**
//     * Get total key string.
//     *
//     * @param className the class name
//     * @param keyId     the key id
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getTotalKey(String className,String keyId){
//        return getKey(className,RedisUtils.TOTAL_KEY,Md5Utils.hash(keyId).substring(0,20));
//    }
//
//    /**
//     * Get find list key string.
//     *
//     * @param className the class name
//     * @param keyId     the key id
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getFindListKey(String className,String keyId){
//        return getKey(className,RedisUtils.FIND_LIST_KEY,Md5Utils.hash(keyId).substring(0,20));
//    }
//
//    /**
//     * Get find list first key string.
//     *
//     * @param className the class name
//     * @param keyId     the key id
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getFindListFirstKey(String className,String keyId){
//        return getKey(className,RedisUtils.FIND_LIST_FIRST_KEY,Md5Utils.hash(keyId).substring(0,20));
//    }
//
//    /**
//     * Get find page key string.
//     *
//     * @param className the class name
//     * @param keyId     the key id
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getFindPageKey(String className,String keyId){
//        return getKey(className,RedisUtils.FIND_PAGE_KEY,Md5Utils.hash(keyId).substring(0,20));
//    }
//
//    /**
//     * Get find list key pattern string.
//     *
//     * @param className the class name
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getFindListKeyPattern(String className){
//        if(className==null){className="";}
//        return RedisUtils.KEY_PREFIX+className+FIND_LIST_KEY_PATTERN;
//    }
//
//    /**
//     * Get fin page key pattern string.
//     *
//     * @param className the class name
//     * @return the string
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public static String getFinPageKeyPattern(String className){
//        if(className==null){className="";}
//        return RedisUtils.KEY_PREFIX+className+FIND_PAGE_KEY_PATTERN;
//    }
//
//    /**
//     * 获取 key 的有效期（秒）
//     *
//     * @param key the key
//     * @return the long
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public long getExpireTime(String key){
//        if(!run()) { return 0l; }
//        long time = redisTemplate.getExpire(key,TimeUnit.SECONDS);
//        return time;
//    }
//
//    /**
//     * 批量删除对应的value
//     *
//     * @param keys the keys
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:39
//     * @company
//     */
//    public void remove(final String... keys) {
//        if(!run()) { return; }
//        try{
//            for (String key : keys) {
//                remove(key);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtils remove:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern the pattern
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public void removePattern(String pattern) {
//        if(!run()) { return;}
//        if(!listFlush()){
//            return ;
//        }
//        try{
//            if(pattern==null) {
//                pattern = "";
//            }
//            Set<String> keys=getKyes(pattern);
//            if (keys.size() > 0) {
//                redisTemplate.delete(keys);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtils removePattern:"+RUN_MESSAGE+e.getMessage(),RUN_MESSAGE+ e.getMessage());
//        }
//    }
//
//    /**
//     * Remove pattern shiro reids.
//     *
//     * @param pattern the pattern
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public void removePatternShiroReids(String pattern){
//        if(!run()) { return;}
//        if(!listFlush()){
//            return ;
//        }
//        try{
//            if(pattern==null) {
//                pattern = "";
//            }
//            Set<String> keys=getKyesShiroReids(pattern);
//            if (keys.size() > 0){
//                stringRedisTemplate.delete(keys);
//                redisTemplate.delete(keys);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtils removePattern:"+RUN_MESSAGE+e.getMessage(),RUN_MESSAGE+ e.getMessage());
//        }
//    }
//
//    /**
//     * 获取keys
//     *
//     * @param pattern the pattern
//     * @return the kyes
//     */
//    public Set<String> getKyes(String pattern) {
//        if(!run()) { return null; }
//        try{
//            if(pattern==null){ pattern=""; }
//            Set<String> keys=stringRedisTemplate.keys("*"+pattern);
//            Set<String> keysnew=new HashSet<String>();
//            Iterator<String> it = keys.iterator();
//            while (it.hasNext()) {
//                keysnew.add(it.next().substring(7));
//            }
//           return keysnew;
//        } catch (Exception e) {
//            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * Gets kyes shiro reids.
//     *
//     * @param pattern the pattern
//     * @return the kyes shiro reids
//     */
//    public Set<String> getKyesShiroReids(String pattern) {
//        if(!run()) { return null; }
//        try{
//            if(pattern==null){ pattern=""; }
//            Set<String> keys=stringRedisTemplate.keys("*"+pattern);
//            Set<String> keysnew=new HashSet<String>();
//            Iterator<String> it = keys.iterator();
//            while (it.hasNext()) {
//                String tr = it.next();
//                if(tr.contains(SHIRO_REDIS)) {
//                    keysnew.add(tr);
//                } else if(tr.contains(SHIRO_REDIS_OBJECT)) {
//                    keysnew.add(tr.substring(8));
//                }
//            }
//            return keysnew;
//        } catch (Exception e) {
//            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * Gets kyes all.
//     *
//     * @return the kyes all
//     */
//    public Set<String> getKyesAll() {
//        if(!run()) { return null; }
//        try{
//            Set<String> keys=stringRedisTemplate.keys("*");
//            Set<String> keysnew=new HashSet<String>();
//            Iterator<String> it = keys.iterator();
//            while (it.hasNext()) {
//                keysnew.add(it.next());
//            }
//            return keysnew;
//        } catch (Exception e) {
//            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * 获取Count
//     *
//     * @return the count
//     */
//    public int getCount() {
//        if(!run()) { return 0; }
//        try{
//            Set<String> keys=stringRedisTemplate.keys("*");
//            return keys.size();
//        } catch (Exception e) {
//            logger.error("RedisUtils getCount:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
//            return 0;
//        }
//    }
//
//    /**
//     * Gets count shiro.
//     *
//     * @return the count shiro
//     */
//    public int getCountShiro() {
//        if(!run()) { return 0; }
//        try{
//            Set<String> keys=stringRedisTemplate.keys(SHIRO_REDIS+"*");
//            return keys.size();
//        } catch (Exception e) {
//            logger.error("RedisUtils getCount:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
//            return 0;
//        }
//    }
//
//    /**
//     * 删除对应的value
//     *
//     * @param key the key
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public void remove(final String key) {
//        if(!run()) { return ; }
//        try{
//            if(key.contains(SHIRO_REDIS)) {
//                stringRedisTemplate.delete(key);
//            }else{
//                redisTemplate.delete(key);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtils exists:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key the key
//     * @return boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public boolean exists(final String key) {
//        if(!run()) { return false; }
//        boolean retuslt=false;
//        try{
//            if(key.contains(SHIRO_REDIS)) {
//                retuslt = stringRedisTemplate.hasKey(key);
//            } else {
//                retuslt = redisTemplate.hasKey(key);
//            }
//        } catch (Exception e) {
//            logger.error("RedisUtils exists:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//        return retuslt;
//    }
//
//    /**
//     * 读取缓存
//     *
//     * @param key the key
//     * @return object
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public Object get(final String key) {
//        if(!run()) { return null; }
//        Object result = null;
//        try{
//            if(key.contains(SHIRO_REDIS)){
//                ValueOperations<String, String> operationsString = stringRedisTemplate.opsForValue();
//                result = operationsString.get(key);
//            }else {
//                ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//                result = operations.get(key);
//            }
//            return result;
//        }catch (Exception e){
//            logger.error("RedisUtils get:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key   the key
//     * @param value the value
//     * @return boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public boolean set(final String key, Object value) {
//        if(!run()) { return false; }
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * 写入缓存
//     *
//     * @param key        the key
//     * @param value      the value
//     * @param expireTime the expire time
//     * @return boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public boolean set(final String key, Object value, Long expireTime) {
//        if(!run()) { return false; }
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//            result = true;
//        } catch (Exception e) {
//            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * Set boolean.
//     *
//     * @param key        the key
//     * @param value      the value
//     * @param expireTime the expire time
//     * @param unit       the unit
//     * @return the boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public boolean set(final String key, Object value, Long expireTime,TimeUnit unit) {
//        if(!run()) { return false; }
//        boolean result = false;
//        try {
//            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
//            redisTemplate.expire(key, expireTime, unit);
//            result = true;
//        } catch (Exception e) {
//            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
//        }
//        return result;
//    }
//
//    private boolean run(){
//        if(Global.getConfig("spring.redis.run")=="true") {
//            return true;
//        }
//        logger.info("未启用spring.redis.run");
//        return false;
//    }
//
//    /**
//     * Is run boolean.
//     *
//     * @return the boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:40
//     * @company
//     */
//    public static boolean isRun(){
//        if(Global.getConfig("spring.redis.run")=="true") {
//            return true;
//        }
//        logger.info("未启用spring.redis.run");
//        return false;
//    }
//
//    /**
//     * Is shire redis boolean.
//     *
//     * @return the boolean
//     * @author PanMeiCheng
//     * @date 2018年12月10日 23:05:41
//     * @company
//     */
//    public static boolean isShireRedis(){
//        if(!isRun()) {
//            return false;
//        }
//        if(Global.getConfig("shiro.redis")!="true") {
//            logger.info("未启用shiro.redis");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean listFlush(){
//        if(Global.getConfig("spring.redis.listFlush")=="true") {
//            return true;
//        }
//        logger.info("未启用spring.redis.listFlush");
//        return false;
//    }
//}