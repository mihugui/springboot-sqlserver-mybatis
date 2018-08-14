package com.ibms.app.service;

import com.ibms.app.beans.CecEquipment;
import com.ibms.app.beans.CecEquipmentExample;

import java.util.List;


public interface CecEquipmentService {

    List<CecEquipment> getAll(CecEquipmentExample cecEquipmentExample);

}
