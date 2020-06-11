package com.mkst.mini.systemplus.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkst.mini.systemplus.system.mapper.SequenceMapper;
import com.mkst.mini.systemplus.system.domain.Sequence;
import com.mkst.mini.systemplus.system.service.ISequenceService;
import com.mkst.mini.systemplus.common.support.Convert;

/**
 * 序列码 服务层实现
 * 
 * @author systemplus
 * @date 2019-03-01
 */
@Service
public class SequenceServiceImpl implements ISequenceService 
{
	@Autowired
	private SequenceMapper sequenceMapper;

	/**
     * 查询序列码信息
     * 
     * @param nAME 序列码ID
     * @return 序列码信息
     */
    @Override
	public Sequence selectSequenceById(String nAME)
	{
	    return sequenceMapper.selectSequenceById(nAME);
	}
	
	/**
     * 查询序列码列表
     * 
     * @param sequence 序列码信息
     * @return 序列码集合
     */
	@Override
	public List<Sequence> selectSequenceList(Sequence sequence)
	{
	    return sequenceMapper.selectSequenceList(sequence);
	}
	
    /**
     * 新增序列码
     * 
     * @param sequence 序列码信息
     * @return 结果
     */
	@Override
	public int insertSequence(Sequence sequence)
	{
	    return sequenceMapper.insertSequence(sequence);
	}
	
	/**
     * 修改序列码
     * 
     * @param sequence 序列码信息
     * @return 结果
     */
	@Override
	public int updateSequence(Sequence sequence)
	{
	    return sequenceMapper.updateSequence(sequence);
	}

	/**
     * 删除序列码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSequenceByIds(String ids)
	{
		return sequenceMapper.deleteSequenceByIds(Convert.toStrArray(ids));
	}

	/* (non-Javadoc)
	 * @see com.mkst.mini.systemplus.system.service.ISequenceService#getKey(java.lang.String)
	 */
	@Override
	public String getKey(String keyName) {
		// TODO Auto-generated method stub
		return sequenceMapper.getKey(keyName);
	}
	
}
