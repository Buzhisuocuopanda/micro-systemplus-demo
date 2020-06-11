package com.mkst.mini.systemplus.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mkst.mini.systemplus.common.base.UserZtree;
import com.mkst.mini.systemplus.system.domain.*;
import com.mkst.mini.systemplus.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkst.mini.systemplus.common.annotation.DataScope;
import com.mkst.mini.systemplus.common.constant.UserConstants;
import com.mkst.mini.systemplus.common.support.Convert;
import com.mkst.mini.systemplus.common.utils.StringUtils;
import com.mkst.mini.systemplus.system.service.ISysUserService;

/**
 * 用户 业务层处理
 * 
 * @author admin
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(tableAlias = "d",userAlias = "u")
    public List<SysUser> selectUserList(SysUser user)
    {
        return userMapper.selectUserList(user);
    }
    
    
    /**
     * 根据条件分页查询已分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(tableAlias = "d", userAlias = "u")
    public List<SysUser> selectAllocatedList(SysUser user)
    {
        return userMapper.selectAllocatedList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception
    {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds)
        {
            if (SysUser.isAdmin(userId))
            {
                throw new Exception("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user)
    {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user)
    {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(SysUser user)
    {
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user)
    {
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long roleId : user.getRoleIds())
        {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user)
    {
        // 新增用户与岗位管理
        List<SysUserPost> list = new ArrayList<SysUserPost>();
        for (Long postId : user.getPostIds())
        {
            SysUserPost up = new SysUserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0)
        {
            userPostMapper.batchUserPost(list);
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
        List<SysRole> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId)
    {
        List<SysPost> list = postMapper.selectPostsByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

	@Override
	public int selectUserRoleId(Long userId) {
		
		return userMapper.selectUserRoleId(userId);
	}

	/**
     * 根据用户权限查询用户
     * @param user
     * @return
     */
    public List<SysUser> selectUserListByRoleIds (SysUser user){
    	return userMapper.selectUserListByRoleIds(user);
    }


    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @DataScope(tableAlias = "d", userAlias = "u")
    public List<SysUser> selectUnallocatedList(SysUser user)
    {
        return userMapper.selectUnallocatedList(user);
    }


	/**
	 * @function 
	 * @author FH
	 * 2019年12月26日 下午3:03:03
	 * @param userName
	 * @return 
	 */
	public SysUser selectUserByUserName(String userName) {
		return userMapper.selectUserByUserName(userName);
	}

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean registerUser(SysUser user) {
        user.setUserType(UserConstants.REGISTER_USER_TYPE);
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public SysUser selectUserByLoginNameAndType(SysUser user) {
        return userMapper.selectUserByLoginNameAndType(user);
    }

    @DataScope(tableAlias = "d")
    public List<UserZtree> selectUserTree(SysDept dept,List<String> userIdList) {
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        List<UserZtree> ztrees = initZtree(deptList,userIdList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<UserZtree> initZtree(List<SysDept> deptList, List<String> userIdList)
    {
        return initZtree(deptList, null, userIdList);
    }

    public List<UserZtree> initZtree(List<SysDept> deptList, List<String> roleDeptList, List<String> userIdList)
    {

        List<UserZtree> ztrees = new ArrayList<UserZtree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SysDept dept : deptList)
        {
            if(dept.getDeptId().toString().equals("110")){
                continue;
            }
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                UserZtree ztree = new UserZtree();
                ztree.setId(Convert.toStr("P_"+dept.getDeptId()));//部门的ID
                ztree.setpId(Convert.toStr("P_"+dept.getParentId()));
                ztree.setName(dept.getDeptName());
                ztree.setTitle("0");
                if (isCheck)
                {
                    ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                }
                ztrees.add(ztree);
                // 根据部门ID去查询用户
                SysUser sysUser = new SysUser();
                sysUser.setDeptId(Convert.toLong(dept.getDeptId()));
                List<SysUser> sysUsers = userMapper.selectUserListByDeptId(sysUser);
                if (sysUsers != null && sysUsers.size() > 0) {
                    for (SysUser us : sysUsers) {
                        UserZtree userZtree = new UserZtree();
                        userZtree.setId(Convert.toStr(us.getUserId()));
                        userZtree.setpId("P_"+dept.getDeptId());
                        userZtree.setName(us.getUserName());
                        userZtree.setTitle("1");// 当作用户标识
                        if (userIdList != null && userIdList.size() > 0) {
                            if (userIdList.contains(Convert.toStr(us.getUserId()))) {
                                userZtree.setChecked(true);
                            }
                        }
                        ztrees.add(userZtree);
                    }
                }
            }
        }
        return ztrees;
    }



}
