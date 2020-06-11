package com.mkst.mini.systemplus.system.web;

import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.common.constant.UserConstants;
import com.mkst.mini.systemplus.common.shiro.session.UserToken;
import com.mkst.mini.systemplus.common.shiro.utils.LoginType;
import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.common.utils.StringUtils;
import com.mkst.mini.systemplus.framework.util.ServletUtils;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.system.service.ISysConfigService;
import com.mkst.mini.systemplus.util.RsaUtil;
import com.mkst.mini.systemplus.util.SysConfigUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 * 
 * @author admin
 */
@Controller
public class SysLoginController extends BaseController
{

    @Autowired
    private ISysConfigService configService;

    private String getLoginPageCode(){
        return configService.selectConfigByKey("login.page");
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        if(user!=null){
            String userType = user.getUserType();
            if(StringUtils.isNotEmpty(userType) && userType.equals(UserConstants.REGISTER_USER_TYPE)){ //前端用户
                return  "redirect:"+Global.getConfig("shiro.user.indexUrl");
            }
            return "redirect:/index";
        }
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        String publics=SysConfigUtil.getKey("publics");
        String modulus=SysConfigUtil.getKey("modulus");
        mmap.put("publics", publics);
        mmap.put("modulus", modulus);
        mmap.put("name",Global.getName());

        String loginPageCode=getLoginPageCode();
        if(StringUtils.isEmpty(loginPageCode)){
            return "login";
        }else{
            //配置了login.page参数
            return "loginPage/"+loginPageCode+"/login";//页面在cms模块loginPage文件夹下
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
    	if(password.length()>50) {
    		password=RsaUtil.decode(password);
    	}

        UserToken token = new UserToken(username, password, LoginType.PASSWORD, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }
}
