package com.ibms.app.service;

import com.github.pagehelper.PageInfo;
import com.ibms.app.beans.CecEquipment;

public interface CecEquipmentService {

    PageInfo<CecEquipment> getAll();

}
