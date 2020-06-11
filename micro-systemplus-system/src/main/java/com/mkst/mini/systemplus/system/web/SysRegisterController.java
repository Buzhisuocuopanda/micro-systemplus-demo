package com.mkst.mini.systemplus.system.web;

import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.common.shiro.service.SysRegisterService;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    private String getRegisterPageCode(){
        return configService.selectConfigByKey("login.page");
    }

    @GetMapping("/register")
    public String register(ModelMap mmap)
    {
        String registerPageCode = getRegisterPageCode();
        mmap.put("name", Global.getName());
        if(StringUtils.isEmpty(registerPageCode)){
            return "register";
        }else{
            //配置了login.page参数
            return "loginPage/"+registerPageCode+"/register";//页面在cms模块loginPage文件夹下
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
