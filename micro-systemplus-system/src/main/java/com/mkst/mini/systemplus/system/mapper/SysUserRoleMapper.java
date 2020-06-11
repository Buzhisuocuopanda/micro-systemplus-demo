package com.mkst.mini.systemplus.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mkst.mini.systemplus.system.domain.SysUserRole;

/**
 * 用户表 数据层
 * 
 * @author admin
 */
public interface SysUserRoleMapper
{
    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<SysUserRole> userRoleList);

	/**
	 * @function 
	 * @author FH
	 * 2019年11月28日 上午10:50:10
	 * @param roleId
	 * @param longArray
	 * @return 
	 */
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

	/**
	 * @function 
	 * @author FH
	 * 2019年11月28日 上午10:56:02
	 * @param userRole
	 * @return 
	 */
	public int deleteUserRoleInfo(SysUserRole userRole);
}
