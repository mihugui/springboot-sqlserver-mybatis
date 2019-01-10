package com.ibms.app.web;

import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.beans.CecEquipment;
import com.ibms.app.service.ValueService;
import com.ibms.app.utils.AjaxResult;
import com.ibms.app.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
  *@ClassNmae TextController
  *@Description TODO
  *@Author PWT
  *@Date 2018/8/14 16:41
  *@Version 1.0
  **/

@RestController
@EnableAutoConfiguration
@RequestMapping("/test")
public class TextController {

    @Autowired
    private ValueService valueService;


    @RequestMapping("list")
    @ResponseBody
    public AjaxResult list(CecDeviceInfoValue cecDeviceInfoValue){
        AjaxResult msg = new AjaxResult();
        try {
            msg.setRetcode(200);
            msg.setData(valueService.list(cecDeviceInfoValue ));
            msg.setRetmsg("查询成功");
        }catch(Exception e){
            e.printStackTrace();
            msg.setRetcode(Globals.HTTP_BAD_REQUEST);
            msg.setRetmsg("操作失败");
        }
        return msg;
    }

    @RequestMapping("listEquipment")
    @ResponseBody
    public AjaxResult listEquipment(CecEquipment cecEquipment){
        AjaxResult msg = new AjaxResult();
        try {
            msg.setRetcode(200);
            msg.setData(valueService.listEquipment(cecEquipment ));
            msg.setRetmsg("查询成功");
        }catch(Exception e){
            e.printStackTrace();
            msg.setRetcode(Globals.HTTP_BAD_REQUEST);
            msg.setRetmsg("操作失败");
        }
        return msg;
    }
}
