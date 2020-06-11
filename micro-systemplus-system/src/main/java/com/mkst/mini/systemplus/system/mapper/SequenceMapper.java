package com.mkst.mini.systemplus.system.mapper;

import com.mkst.mini.systemplus.system.domain.Sequence;

import java.util.List;	

import org.apache.ibatis.annotations.Select;

/**
 * 序列码 数据层
 * 
 * @author systemplus
 * @date 2019-03-01
 */
public interface SequenceMapper 
{
	/**
     * 查询序列码信息
     * 
     * @param nAME 序列码ID
     * @return 序列码信息
     */
	public Sequence selectSequenceById(String name);
	
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
     * 删除序列码
     * 
     * @param nAME 序列码ID
     * @return 结果
     */
	public int deleteSequenceById(String name);
	
	/**
     * 批量删除序列码
     * 
     * @param nAMEs 需要删除的数据ID
     * @return 结果
     */
	public int deleteSequenceByIds(String[] names);
	
	@Select("SELECT TH_NEXTSEQ(#{keyName,jdbcType=VARCHAR}) FROM DUAL")
	public String getKey(String keyName);
	
}