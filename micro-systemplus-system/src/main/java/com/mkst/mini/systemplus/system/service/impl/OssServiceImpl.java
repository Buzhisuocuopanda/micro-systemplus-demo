package com.mkst.mini.systemplus.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkst.mini.systemplus.common.support.Convert;
import com.mkst.mini.systemplus.system.domain.Oss;
import com.mkst.mini.systemplus.system.mapper.OssMapper;
import com.mkst.mini.systemplus.system.service.IOssService;

/**
 * 附件管理 服务层实现
 * 
 * @author systemplus
 * @date 2019-03-19
 */
@Service
public class OssServiceImpl implements IOssService 
{
	@Autowired
	private OssMapper ossMapper;

	/**
     * 查询附件管理信息
     * 
     * @param id 附件管理ID
     * @return 附件管理信息
     */
    @Override
	public Oss selectOssById(Integer id)
	{
	    return ossMapper.selectOssById(id);
	}
	
	/**
     * 查询附件管理列表
     * 
     * @param oss 附件管理信息
     * @return 附件管理集合
     */
	@Override
	public List<Oss> selectOssList(Oss oss)
	{
	    return ossMapper.selectOssList(oss);
	}
	
    /**
     * 新增附件管理
     * 
     * @param oss 附件管理信息
     * @return 结果
     */
	@Override
	public int insertOss(Oss oss)
	{
	    return ossMapper.insertOss(oss);
	}
	
	/**
     * 修改附件管理
     * 
     * @param oss 附件管理信息
     * @return 结果
     */
	@Override
	public int updateOss(Oss oss)
	{
	    return ossMapper.updateOss(oss);
	}

	/**
     * 删除附件管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOssByIds(String ids)
	{
		return ossMapper.deleteOssByIds(Convert.toStrArray(ids));
	}
	
}
