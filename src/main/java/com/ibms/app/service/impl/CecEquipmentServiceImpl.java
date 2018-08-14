package com.ibms.app.service.impl;

import com.ibms.app.beans.CecEquipment;
import com.ibms.app.beans.CecEquipmentExample;
import com.ibms.app.dao.CecEquipmentMapper;
import com.ibms.app.service.CecEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CecEquipmentServiceImpl implements CecEquipmentService {

    @Autowired
    CecEquipmentMapper cecEquipmentMapper;

    @Override
    public List<CecEquipment> getAll(CecEquipmentExample cecEquipmentExample) {

        return cecEquipmentMapper.selectByExample(cecEquipmentExample);
    }
}
