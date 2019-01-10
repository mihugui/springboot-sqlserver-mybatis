package com.ibms.app.service.impl;

import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.beans.CecEquipment;
import com.ibms.app.dao.master.CecDeviceInfoValueMapper;
import com.ibms.app.dao.other.CecEquipmentMapper;
import com.ibms.app.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ValueServiceImpl
 * @User: PWT
 * @Date: 2019/1/10 10:28
 * @Version 1.0
 * Description: No Description
 */
@Service
public class ValueServiceImpl implements ValueService {

    @Autowired
    CecDeviceInfoValueMapper cecDeviceInfoValueMapper;

    @Autowired
    CecEquipmentMapper cecEquipmentMapper;

    @Override
    public List<CecDeviceInfoValue> list(CecDeviceInfoValue cecDeviceInfoValue) {
        return cecDeviceInfoValueMapper.getByBean(cecDeviceInfoValue);
    }

    @Override
    public List<CecEquipment> listEquipment(CecEquipment cecEquipment) {
        return cecEquipmentMapper.getByBean(cecEquipment);
    }
}
