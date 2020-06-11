package com.mkst.mini.systemplus.common.shiroredis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

public class RedisManager extends WorkAloneRedisManager implements IRedisManager {

	private static final String DEFAULT_HOST = "127.0.0.1:6379";
	private String host = DEFAULT_HOST;

	// timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = Protocol.DEFAULT_TIMEOUT;

	private String password;

	private int database = Protocol.DEFAULT_DATABASE;

	private int port;
	
	private int expire;
	
	private JedisPool jedisPool;

	private void init() {
		synchronized (this) {
			if (jedisPool == null) {
				String[] hostAndPort = host.split(":");
				jedisPool = new JedisPool(getJedisPoolConfig(), hostAndPort[0], Integer.parseInt(hostAndPort[1]), expire==0?timeout:expire, password, database);
			}
		}
	}

	@Override
	protected Jedis getJedis() {
		if (jedisPool == null) {
			init();
		}
		return jedisPool.getResource();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the expire
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}
	
}
