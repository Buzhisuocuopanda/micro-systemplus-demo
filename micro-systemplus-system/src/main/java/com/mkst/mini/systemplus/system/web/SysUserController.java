package com.mkst.mini.systemplus.system.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mkst.mini.systemplus.common.base.UserZtree;
import com.mkst.mini.systemplus.common.base.Ztree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkst.mini.systemplus.common.annotation.Log;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.enums.BusinessType;
import com.mkst.mini.systemplus.common.shiro.service.SysPasswordService;
import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.common.utils.ExcelUtil;
import com.mkst.mini.systemplus.common.utils.StringUtils;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.system.domain.SysDept;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.system.service.ISysDeptService;
import com.mkst.mini.systemplus.system.service.ISysPostService;
import com.mkst.mini.systemplus.system.service.ISysRoleService;
import com.mkst.mini.systemplus.system.service.ISysUserService;

/**
 * 用户信息
 * 
 * @author admin
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;
    
    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    
    @RequiresPermissions("system:user:list")
    @PostMapping("/listByRole")
    @ResponseBody
    public TableDataInfo listByRole(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserListByRoleIds(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add/{deptId}")
    public String add(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        SysDept dept = deptService.selectDeptById(deptId);
        if(dept == null) {
        	dept = new SysDept();
        }
        mmap.put("dept", dept);
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员用户");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员用户");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user)
    {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(userService.resetUserPwd(user));
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(userService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user)
    {
        return userService.checkEmailUnique(user);
    }

    @GetMapping(value="/selectUserTree")
    public String selectUserTree(ModelMap model,String userIds) {
        model.addAttribute("userIds", userIds);
        return prefix + "/selectUserTree";
    }

    /**
     * 加载用户列表树
     */
    @GetMapping("/selectUserTreeAjax")
    @ResponseBody
    public List<UserZtree> treeData(String userIds) {
        String[] userIdArray = null;
        List<String> userIdList = new ArrayList<String>();
        if (StringUtils.isNotBlank(userIds)) {
            userIdArray = userIds.split(",");
            if (userIdArray != null) {
                userIdList = Arrays.asList(userIdArray);
            }
        }
        List<UserZtree> ztrees = userService.selectUserTree(new SysDept(),userIdList);
        return ztrees;
    }
}