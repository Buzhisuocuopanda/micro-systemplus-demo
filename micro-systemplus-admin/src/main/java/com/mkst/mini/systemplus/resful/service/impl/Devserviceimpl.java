package com.mkst.mini.systemplus.resful.service.impl;

import com.mkst.mini.systemplus.common.shiro.utils.ShiroUtils;
import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.resful.domains.Devdto;
import com.mkst.mini.systemplus.resful.service.Devservice;
import com.mkst.mini.systemplus.resful.utils.BeanCopyUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class Devserviceimpl implements Devservice {

    @Transactional
    @Override
    public int insert(Devdto devdto) {

        Date date = new Date();
        DevDeviceBase devDeviceBase = BeanCopyUtils.coypToClass(devdto, DevDeviceBase.class, null);
        devDeviceBase.setCreateTime(date);
        devDeviceBase.setCreateBy(ShiroUtils.getLoginName());

        return 0;
    }
}
