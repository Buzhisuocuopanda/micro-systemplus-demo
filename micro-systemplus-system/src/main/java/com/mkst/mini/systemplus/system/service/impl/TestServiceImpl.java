package com.mkst.mini.systemplus.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkst.mini.systemplus.system.mapper.TestMapper;
import com.mkst.mini.systemplus.system.service.TestServiceI;

/**
 *
 *@author PanMeiCheng
 *@date 2018-12-11
 *@company DM
 *@version 1.0
 */
@Service
public class TestServiceImpl implements TestServiceI {
    @Autowired
    TestMapper testMapper;

    @Override
    public List<Map> getAllUsers() {
        return testMapper.getAllUsers();
    }

    @Override
    public List<Map> getAllUsers(String sql) {
        return testMapper.getAllUsersBySql(sql);
    }
}
