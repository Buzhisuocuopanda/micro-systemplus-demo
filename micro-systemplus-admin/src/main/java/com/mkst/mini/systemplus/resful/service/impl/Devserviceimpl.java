package com.mkst.mini.systemplus.resful.service.impl;

import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.resful.domains.Devdto;
import com.mkst.mini.systemplus.resful.mapper.DevDeviceBaseMapper;
import com.mkst.mini.systemplus.resful.service.Devservice;
import com.mkst.mini.systemplus.resful.utils.BeanCopyUtilss;
import com.mkst.mini.systemplus.resful.utils.BeanCopyUtilss;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class Devserviceimpl implements Devservice {


    @Resource
    private DevDeviceBaseMapper devDeviceBaseMapper;

    @Transactional
    @Override
    public int insert(Devdto devdto) {

        Date date = new Date();
        DevDeviceBase devDeviceBase = BeanCopyUtilss.coypToClass(devdto, DevDeviceBase.class, null);
        devDeviceBase.setCreateTime(date);
        devDeviceBase.setCreateBy(ShiroUtils.getLoginName());
        devDeviceBase.setDelFlag("0");
        devDeviceBaseMapper.insertSelective(devDeviceBase);


        return 1;
    }

    @Override
    public int edit(Devdto devdto) {
        Date date = new Date();
        DevDeviceBase devDeviceBase = BeanCopyUtilss.coypToClass(devdto, DevDeviceBase.class, null);
        devDeviceBase.setUpdateTime(date);
        devDeviceBase.setUpdateBy(ShiroUtils.getLoginName());
        devDeviceBase.setDelFlag("0");
        devDeviceBaseMapper.updateByPrimaryKeySelective(devDeviceBase);
    return 1;
    }

    @Override
    public int devdelt(String devId) {
        DevDeviceBase devDeviceBase = new DevDeviceBase();
        devDeviceBase.setDelFlag("1");
        devDeviceBase.setId(Long.valueOf(devId));
        devDeviceBase.setUpdateTime(new Date());
        devDeviceBase.setUpdateBy(ShiroUtils.getLoginName());
        devDeviceBaseMapper.updateByPrimaryKeySelective(devDeviceBase);
return 1;
    }

    @Override
    public List<DevDeviceBaseVo> selectdev(Devdto devdto) {
        return devDeviceBaseMapper.selectdev(devdto);
    }

    @Override
    public DevDeviceBaseVo devdetail(Devdto devdto) {
        return null;
    }
}
