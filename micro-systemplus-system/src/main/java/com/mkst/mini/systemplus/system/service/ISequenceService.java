package com.mkst.mini.systemplus.system.service;

import com.mkst.mini.systemplus.system.domain.Sequence;

import java.util.List;

/**
 * 序列码 服务层
 * 
 * @author systemplus
 * @date 2019-03-01
 */
public interface ISequenceService 
{
	/**
     * 查询序列码信息
     * 
     * @param nAME 序列码ID
     * @return 序列码信息
     */
	public Sequence selectSequenceById(String nAME);
	
	/**
     * 查询序列码列表
     * 
     * @param sequence 序列码信息
     * @return 序列码集合
     */
	public List<Sequence> selectSequenceList(Sequence sequence);
	
	/**
     * 新增序列码
     * 
     * @param sequence 序列码信息
     * @return 结果
     */
	public int insertSequence(Sequence sequence);
	
	/**
     * 修改序列码
     * 
     * @param sequence 序列码信息
     * @return 结果
     */
	public int updateSequence(Sequence sequence);
		
	/**
     * 删除序列码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSequenceByIds(String ids);
	
	public String getKey(String keyName);
	
}
