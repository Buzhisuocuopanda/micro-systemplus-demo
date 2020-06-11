package com.mkst.mini.systemplus.system.service;

import java.util.List;

import com.mkst.mini.systemplus.system.domain.Oss;

/**
 * 附件管理 服务层
 * 
 * @author systemplus
 * @date 2019-03-19
 */
public interface IOssService 
{
	/**
     * 查询附件管理信息
     * 
     * @param id 附件管理ID
     * @return 附件管理信息
     */
	public Oss selectOssById(Integer id);
	
	/**
     * 查询附件管理列表
     * 
     * @param oss 附件管理信息
     * @return 附件管理集合
     */
	public List<Oss> selectOssList(Oss oss);
	
	/**
     * 新增附件管理
     * 
     * @param oss 附件管理信息
     * @return 结果
     */
	public int insertOss(Oss oss);
	
	/**
     * 修改附件管理
     * 
     * @param oss 附件管理信息
     * @return 结果
     */
	public int updateOss(Oss oss);
		
	/**
     * 删除附件管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOssByIds(String ids);
	
}
