package com.mkst.mini.systemplus.resful.mapper;

import com.mkst.mini.systemplus.domain.DevDeviceBase;
import com.mkst.mini.systemplus.domain.vo.DevDeviceBaseVo;
import com.mkst.mini.systemplus.resful.domains.DevDeviceBaseCriteria;
import com.mkst.mini.systemplus.resful.domains.Devdto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevDeviceBaseMapper {
    long countByExample(DevDeviceBaseCriteria example);

    int deleteByExample(DevDeviceBaseCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(DevDeviceBase record);

    int insertSelective(DevDeviceBase record);

    List<DevDeviceBase> selectByExample(DevDeviceBaseCriteria example);

    DevDeviceBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DevDeviceBase record, @Param("example") DevDeviceBaseCriteria example);

    int updateByExample(@Param("record") DevDeviceBase record, @Param("example") DevDeviceBaseCriteria example);

    int updateByPrimaryKeySelective(DevDeviceBase record);

    int updateByPrimaryKey(DevDeviceBase record);

    DevDeviceBase selectDevDeviceBaseById(Long id);

    List<DevDeviceBase> selectDevDeviceBaseList(DevDeviceBase devDeviceBase);

    List<DevDeviceBaseVo> selectdetail(DevDeviceBase selectMeeting);

    List<DevDeviceBaseVo> selectdev(Devdto devdto);
}
