package com.ibms.app.web;

import com.ibms.app.beans.CecEquipment;
import com.ibms.app.beans.CecEquipmentExample;
import com.ibms.app.service.CecEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class TextController {

    @Autowired
    private CecEquipmentService cecEquipmentService;

    @RequestMapping("hello")
    @ResponseBody
    public List<CecEquipment> getEquipment(CecEquipmentExample cecEquipmentExample){
        return cecEquipmentService.getAll(cecEquipmentExample);
    }
}
