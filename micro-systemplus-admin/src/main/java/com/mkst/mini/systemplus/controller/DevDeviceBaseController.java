package com.mkst.mini.systemplus.controller;

import com.mkst.mini.systemplus.common.annotation.Log;
import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.common.enums.BusinessType;
import com.mkst.mini.systemplus.common.utils.ExcelUtil;
import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.service.IDevDeviceBaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备基本信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-06
 */
@Controller
@RequestMapping("/system/base")
public class DevDeviceBaseController extends BaseController
{
    private String prefix = "base";

    @Autowired
    private IDevDeviceBaseService devDeviceBaseService;

    @RequiresPermissions("system:base:view")
    @GetMapping()
    public String base()
    {
        return prefix + "/base";
    }

    /**
     * 查询设备基本信息列表
     */
    @RequiresPermissions("system:base:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DevDeviceBase devDeviceBase)
    {
        startPage();
        List<DevDeviceBase> list = devDeviceBaseService.selectDevDeviceBaseList(devDeviceBase);
        return getDataTable(list);
    }

    /**
     * 导出设备基本信息列表
     */
    @RequiresPermissions("system:base:export")
    @Log(title = "设备基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DevDeviceBase devDeviceBase)
    {
        List<DevDeviceBase> list = devDeviceBaseService.selectDevDeviceBaseList(devDeviceBase);
        ExcelUtil<DevDeviceBase> util = new ExcelUtil<DevDeviceBase>(DevDeviceBase.class);
        return util.exportExcel(list, "设备基本信息数据");
    }

    /**
     * 新增设备基本信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设备基本信息
     */
    @RequiresPermissions("system:base:add")
    @Log(title = "设备基本信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DevDeviceBase devDeviceBase)
    {
        return toAjax(devDeviceBaseService.insertDevDeviceBase(devDeviceBase));
    }

    /**
     * 修改设备基本信息
     */
    @RequiresPermissions("system:base:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DevDeviceBase devDeviceBase = devDeviceBaseService.selectDevDeviceBaseById(id);
        mmap.put("devDeviceBase", devDeviceBase);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备基本信息
     */
    @RequiresPermissions("system:base:edit")
    @Log(title = "设备基本信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DevDeviceBase devDeviceBase)
    {
        return toAjax(devDeviceBaseService.updateDevDeviceBase(devDeviceBase));
    }

    /**
     * 删除设备基本信息
     */
    @RequiresPermissions("system:base:remove")
    @Log(title = "设备基本信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(devDeviceBaseService.deleteDevDeviceBaseByIds(ids));
    }



    @RequiresPermissions("system:base:edit")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap map) {

        // 结果的主体信息
        DevDeviceBase selectMeeting = new DevDeviceBase();
        selectMeeting.setId(id);
        DevDeviceBaseVo deviceBaseVo = devDeviceBaseService.selectdetail(selectMeeting).get(0);


        map.put("base", deviceBaseVo);

        return prefix + "/detail";

    }

}
