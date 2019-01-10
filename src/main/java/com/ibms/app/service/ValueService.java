package com.ibms.app.service;

import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.beans.CecEquipment;

import java.util.List;

/**
 * @ClassName: ValueService
 * @User: PWT
 * @Date: 2019/1/10 10:22
 * @Version 1.0
 * Description: No Description
 */
public interface ValueService {
    List<CecDeviceInfoValue> list(CecDeviceInfoValue cecDeviceInfoValue);

    List<CecEquipment> listEquipment (CecEquipment cecEquipment);
}
