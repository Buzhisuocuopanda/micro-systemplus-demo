package com.mkst.mini.systemplus.service;

import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备基本信息Service接口
 *
 * @author ruoyi
 * @date 2023-03-06
 */
@Service
public interface IDevDeviceBaseService
{
    /**
     * 查询设备基本信息
     *
     * @param id 设备基本信息主键
     * @return 设备基本信息
     */
    public DevDeviceBase selectDevDeviceBaseById(Long id);

    /**
     * 查询设备基本信息列表
     *
     * @param devDeviceBase 设备基本信息
     * @return 设备基本信息集合
     */
    public List<DevDeviceBase> selectDevDeviceBaseList(DevDeviceBase devDeviceBase);

    /**
     * 新增设备基本信息
     *
     * @param devDeviceBase 设备基本信息
     * @return 结果
     */
    public int insertDevDeviceBase(DevDeviceBase devDeviceBase);

    /**
     * 修改设备基本信息
     *
     * @param devDeviceBase 设备基本信息
     * @return 结果
     */
    public int updateDevDeviceBase(DevDeviceBase devDeviceBase);

    /**
     * 批量删除设备基本信息
     *
     * @param ids 需要删除的设备基本信息主键集合
     * @return 结果
     */
    public int deleteDevDeviceBaseByIds(String ids);

    /**
     * 删除设备基本信息信息
     *
     * @param id 设备基本信息主键
     * @return 结果
     */
    public int deleteDevDeviceBaseById(Long id);

    //查询详情
    List<DevDeviceBaseVo> selectdetail(DevDeviceBase selectMeeting);
}
