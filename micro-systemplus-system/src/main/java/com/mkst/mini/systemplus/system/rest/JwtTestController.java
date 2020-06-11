package com.mkst.mini.systemplus.system.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.github.pagehelper.PageHelper;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.system.domain.SysUser;
import com.mkst.mini.systemplus.system.service.impl.TestServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jwt/test")
@Api(value="JWT示范接口", description="JWT示范接口")
public class JwtTestController extends BaseController {
    @Autowired
    TestServiceImpl testService;


    @GetMapping(value = "/test")
    @ApiOperation(value="测试Callable", notes="测试 Callable; Callable类型 swagger 不能使用try,故实际开发建议使用 WebAsyncTask ,后者也是要强大的多")
    public Callable test(){
        System.out.println(new Date());
        return ()->{
            PageHelper.startPage(1,1);
            List<Map> allUsers = testService.getAllUsers();
            TableDataInfo dataTable = getDataTable(allUsers);
            System.out.println(new Date());
            return AjaxResult.success(dataTable);
        };
    }
    @PostMapping(value = "/test2")
    @ApiOperation(value="测试WebAsyncTask", notes="测试WebAsyncTask")
    public WebAsyncTask test2(HttpServletRequest request){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return "5464";
        });
        return test2;
    }
    @PostMapping(value = "/test3")
    @ApiOperation(value="测试body", notes="测试body")
    public WebAsyncTask test3(HttpServletRequest request,@RequestBody String bodyData){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return bodyData;
        });
        return test2;
    }
    @PostMapping(value = "/test4")
    @ApiOperation(value="测试beanBody", notes="测试beanBody")
    public WebAsyncTask test4(HttpServletRequest request,@RequestBody SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }
    @PostMapping(value = "/test5")
    @ApiOperation(value="测试bean", notes="测试bean")
    public WebAsyncTask test5(HttpServletRequest request, SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }

}