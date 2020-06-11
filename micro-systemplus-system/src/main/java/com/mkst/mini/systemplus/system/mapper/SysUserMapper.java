package com.mkst.mini.systemplus.system.mapper;

import java.util.List;

import com.mkst.mini.systemplus.system.domain.SysUser;

/**
 * 用户表 数据层
 * 
 * @author admin
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public SysUser selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SysUser selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);
    
    /**
     * 根据用户id
     * 
     * @param  userId登录用户id
     * @return 结果
     */
    public int selectUserRoleId(Long userId);
    
    /**
     * 根据用户权限查询用户
     * @param user
     * @return
     */
    public List<SysUser> selectUserListByRoleIds (SysUser user);

	/**
	 * @function 
	 * @author FH
	 * 2019年11月27日 下午6:26:16
	 * @param user
	 * @return 
	 */
	public List<SysUser> selectAllocatedList(SysUser user);

	/**
	 * @function 
	 * @author FH
	 * 2019年11月28日 上午10:40:53
	 * @param user
	 * @return 
	 */
	public List<SysUser> selectUnallocatedList(SysUser user);

	/**
	 * @function 
	 * @author FH
	 * 2019年12月26日 下午3:03:32
	 * @param userName
	 * @return 
	 */
	public SysUser selectUserByUserName(String userName);

    public  List<SysUser> selectUserListByDeptId(SysUser sysUser);

    /**
     * @function 根据登录类型及用户id查询用户
     * @author liuzhiping
     * 2020年5月11日
     * @param sysUser
     * @return
     */
	public SysUser selectUserByLoginNameAndType(SysUser sysUser);
}
