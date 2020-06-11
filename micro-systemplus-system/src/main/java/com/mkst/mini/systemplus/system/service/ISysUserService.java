package com.mkst.mini.systemplus.system.service;

import java.util.List;

import com.mkst.mini.systemplus.common.base.UserZtree;
import com.mkst.mini.systemplus.system.domain.SysDept;
import com.mkst.mini.systemplus.system.domain.SysUser;

/**
 * 用户 业务层
 * 
 * @author admin
 */
public interface ISysUserService
{
    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);

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
     * @throws Exception 异常
     */
    public int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean registerUser(SysUser user);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(SysUser user);

    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int resetUserPwd(SysUser user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);

    /**
     * 根据用户ID查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserPostGroup(Long userId);
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
	 * 2019年11月27日 下午6:24:37
	 * @param user
	 * @return 
	 */
	public List<SysUser> selectAllocatedList(SysUser user);

	/**
	 * @function 
	 * @author FH
	 * 2019年11月28日 上午10:39:45
	 * @param user
	 * @return 
	 */
	public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * @function 根据登录类型及用户id查询用户
     * @author liuzhiping
     * 2020年5月11日
     * @param user
     * @return
     */
    public SysUser selectUserByLoginNameAndType(SysUser user);


    public List<UserZtree> selectUserTree(SysDept dept,List<String> userIdList);
}
