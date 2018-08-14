package com.ibms.app.service;

import com.github.pagehelper.PageInfo;
import com.ibms.app.beans.CecEquipment;

/**
  *@ClassNmae CecEquipmentService
  *@Description 设备管理
  *@Author PWT
  *@Date 2018/8/14 16:40
  *@Version 1.0
  **/

public interface CecEquipmentService {

    PageInfo<CecEquipment> getAll();

}
