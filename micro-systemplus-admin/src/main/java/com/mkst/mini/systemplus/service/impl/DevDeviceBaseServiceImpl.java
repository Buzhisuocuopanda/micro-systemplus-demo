package com.mkst.mini.systemplus.service.impl;

import com.mkst.mini.systemplus.common.enums.DevType;
import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.common.support.Convert;
import com.mkst.mini.systemplus.common.utils.DateUtils;
import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.resful.mapper.DevDeviceBaseMapper;
import com.mkst.mini.systemplus.service.IDevDeviceBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 设备基本信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-06
 */
public class DevDeviceBaseServiceImpl implements IDevDeviceBaseService
{
    @Autowired
    private DevDeviceBaseMapper devDeviceBaseMapper;

    /**
     * 查询设备基本信息
     *
     * @param id 设备基本信息主键
     * @return 设备基本信息
     */
    @Override
    public DevDeviceBase selectDevDeviceBaseById(Long id)
    {
        return devDeviceBaseMapper.selectDevDeviceBaseById(id);
    }





    /**
     * 查询设备基本信息列表
     *
     * @param devDeviceBase 设备基本信息
     * @return 设备基本信息
     */
    @Override
    public List<DevDeviceBase> selectDevDeviceBaseList(DevDeviceBase devDeviceBase)
    {
        List<DevDeviceBase> devDeviceBases = devDeviceBaseMapper.selectDevDeviceBaseList(devDeviceBase);
        devDeviceBases.forEach(devDeviceBase1 -> {
            devDeviceBase1.setDevType(devDeviceBase1.getDevType().equals(DevType.PHONE.getValue())?DevType.PHONE.getName():(devDeviceBase1.getDevType().equals(DevType.PC.getValue())?DevType.PC.getName():DevType.CAMERA.getName()));
           // devDeviceBase1.setDevStatus(devDeviceBase1.getDevStatus().equals("1")?"正常":"异常");
        });
        return devDeviceBases;
    }

    /**
     * 新增设备基本信息
     *
     * @param devDeviceBase 设备基本信息
     * @return 结果
     */
    @Override
    public int insertDevDeviceBase(DevDeviceBase devDeviceBase)
    {
        devDeviceBase.setCreateTime(DateUtils.getNowDate());
        devDeviceBase.setCreateBy(ShiroUtils.getLoginName());
      //  devDeviceBase.setDeptId(String.valueOf(ShiroUtils.getSysUser().getDeptId()));
        devDeviceBase.setDelFlag("0");
        return devDeviceBaseMapper.insertSelective(devDeviceBase);
    }

    /**
     * 修改设备基本信息
     *
     * @param devDeviceBase 设备基本信息
     * @return 结果
     */
    @Override
    public int updateDevDeviceBase(DevDeviceBase devDeviceBase)
    {
        devDeviceBase.setUpdateTime(DateUtils.getNowDate());
        return devDeviceBaseMapper.updateByPrimaryKeySelective(devDeviceBase);
    }

    /**
     * 批量删除设备基本信息
     *
     * @param ids 需要删除的设备基本信息主键
     * @return 结果
     */
    @Override
    public int deleteDevDeviceBaseByIds(String ids)
    {
        return 1;
      //  return devDeviceBaseMapper.deleteDevDeviceBaseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备基本信息信息
     *
     * @param id 设备基本信息主键
     * @return 结果
     */
    @Override
    public int deleteDevDeviceBaseById(Long id)
    {
        DevDeviceBase devDeviceBase = new DevDeviceBase();
        devDeviceBase.setId(id);
        devDeviceBase.setDelFlag("1");
        return devDeviceBaseMapper.updateByPrimaryKeySelective(devDeviceBase);
    }

    @Override
    public List<DevDeviceBaseVo> selectdetail(DevDeviceBase selectMeeting) {
        return devDeviceBaseMapper.selectdetail(selectMeeting);

    }
}
