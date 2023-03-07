package com.mkst.mini.systemplus.resful.controller;

import com.mkst.mini.systemplus.common.base.AjaxResult;
import com.mkst.mini.systemplus.common.base.BaseController;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.framework.web.page.TableDataInfo;
import com.mkst.mini.systemplus.resful.domains.Devdto;
import com.mkst.mini.systemplus.resful.service.Devservice;
import com.mkst.mini.systemplus.resful.utils.ValidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 设备管理Controller
 *
 * @author lhy
 * &#064;date  2020-03-06
 */
@Api(
        tags = {"设备管理"}
)
@Slf4j
@RestController
@RequestMapping("/system/devs")
public class DevController extends BaseController {

    @Resource
    private Devservice devservice;
    /**
     * 新增设备
     */
    @ApiOperation(
            value ="新增设备",
            notes = "新增设备"
    )
    @PostMapping("/devadd")
    public AjaxResult devadd(@Valid @RequestBody Devdto devdto, BindingResult bindingResult) {
            ValidUtils.bindvaild(bindingResult);
        int insert = devservice.insert(devdto);
        return AjaxResult.success(insert);

    }


    /**
     * 修改设备
     */
    @ApiOperation(
            value ="修改设备",
            notes = "修改设备"
    )
    @PostMapping("/devedit")
    public AjaxResult devedit(@Valid @RequestBody Devdto devdto, BindingResult bindingResult) {
        ValidUtils.bindvaild(bindingResult);
        int insert = devservice.edit(devdto);
        return AjaxResult.success(insert);

    }

    /**
     * 删除设备
     */
    @ApiOperation(
            value ="删除设备",
            notes = "删除设备"
    )
    @PostMapping("/devdelt")
    public AjaxResult devdelt(String devId) {
        int devdelt = devservice.devdelt(devId);
        return AjaxResult.success(devdelt);

    }


    /**
     * 采购订单查询
     */
    @ApiOperation(
            value ="采购订单查询",
            notes = "采购订单查询"
    )
    @GetMapping("/devlist")
    public AjaxResult<TableDataInfo> swJsGoodslist(Devdto devdto) {

            startPage();
            List<DevDeviceBaseVo> list = devservice.selectdev(devdto);
            return AjaxResult.success(getDataTable(list));

    }


    /**
     * 设备详情
     */
    @ApiOperation(
            value ="设备详情",
            notes = "设备详情"
    )
    @GetMapping("/devdetail")
    public DevDeviceBaseVo devdetail(Devdto devdto) {

        startPage();
        DevDeviceBaseVo list = devservice.devdetail(devdto);
        return list;

    }


}
