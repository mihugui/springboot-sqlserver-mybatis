package com.ibms.app.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ibms.app.beans.CecEquipment;
import com.ibms.app.service.CecEquipmentService;
import com.ibms.app.utils.AjaxResult;
import com.ibms.app.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private CecEquipmentService cecEquipmentService;

    @RequestMapping("hello")
    @ResponseBody
    public AjaxResult getEquipment(){
        AjaxResult msg = new AjaxResult();

        try {
            msg.setRetcode(200);
            msg.setData(cecEquipmentService.getAll());
            msg.setRetmsg("查询成功");
        }catch(Exception e){
            e.printStackTrace();
            msg.setRetcode(Globals.HTTP_BAD_REQUEST);
            msg.setRetmsg("操作失败");
        }
        return msg;
    }
}
