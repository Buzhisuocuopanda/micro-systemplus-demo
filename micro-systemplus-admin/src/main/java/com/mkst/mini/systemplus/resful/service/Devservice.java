package com.mkst.mini.systemplus.resful.service;

import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.resful.domains.Devdto;

import java.util.List;

public interface Devservice {


    int insert(Devdto devdto);

    int edit(Devdto devdto);

    int devdelt(String devId);

    List<DevDeviceBaseVo> selectdev(Devdto devdto);

    DevDeviceBaseVo devdetail(Devdto devdto);
}
