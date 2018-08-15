package com.ibms.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibms.app.beans.CecEquipment;
import com.ibms.app.beans.CecEquipmentExample;
import com.ibms.app.dao.CecEquipmentMapper;
import com.ibms.app.service.CecEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
  *@ClassNmae CecEquipmentServiceImpl
  *@Description 设备服务
  *@Author PWT
  *@Date 2018/8/14 16:40
  *@Version 1.0
  **/

@Service
public class CecEquipmentServiceImpl implements CecEquipmentService {

    @Autowired
    CecEquipmentMapper cecEquipmentMapper;

    @Override
    public PageInfo<CecEquipment> getAll() {
        Page<CecEquipment> page = PageHelper.startPage(2, 10);
        //PageHelper.orderBy();
        CecEquipmentExample cecEquipmentExample = new CecEquipmentExample();
        cecEquipmentExample.setOrderByClause("EQUIPMENT_ID");

        List<CecEquipment> pagelist = cecEquipmentMapper.selectByExample(cecEquipmentExample);
        PageInfo<CecEquipment> pageInfo = new PageInfo<CecEquipment>(pagelist);
        return pageInfo;
    }
}
