package com.mkst.mini.systemplus.system.web;

import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.common.constant.UserConstants;
import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.common.utils.StringUtils;
import com.mkst.mini.systemplus.system.domain.SysMenu;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.system.service.ISysConfigService;
import com.mkst.mini.systemplus.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    private String getAdminIndex(){
        return configService.selectConfigByKey(UserConstants.KEY_ADMIN_INDEX);
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("name", Global.getName());
        mmap.put("copyrightYear", Global.getCopyrightYear());
        String userType = user.getUserType();
        if(StringUtils.isNotEmpty(userType) && userType.equals(UserConstants.REGISTER_USER_TYPE)){ //前端用户
            return  "redirect:"+Global.getConfig("shiro.user.indexUrl");
        }
        String indePage = this.getAdminIndex();
        if(StringUtils.isNotEmpty(indePage)&&UserConstants.ADMIN_INDEX_TOP_MENU.equals(indePage)){
            return UserConstants.ADMIN_INDEX_TOP_MENU; // index_topMenu
        }else{
            return UserConstants.ADMIN_INDEX_INDEX;
        }
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统首页顶部菜单
    @PostMapping("/index/getMenu")
    @ResponseBody
    public AjaxResult getMenu(HttpServletRequest request)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        return AjaxResult.success(menus);
    }
}
