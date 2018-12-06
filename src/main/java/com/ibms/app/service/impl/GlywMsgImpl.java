package com.ibms.app.service.impl;

import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.dao.CecDeviceInfoValueMapper;
import com.ibms.app.service.GlywMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassNmae GlywMsgImpl
 * @Description TODO
 * @Author PWT
 * @Date 2018/11/14 14:55
 * @Version 1.0
 **/

@Service
public class GlywMsgImpl implements GlywMsg {

    @Autowired
    CecDeviceInfoValueMapper cecDeviceInfoValueMapper;

    protected Logger logger = LoggerFactory.getLogger(GlywMsgImpl.class);
    @Override
    public void DataAnalysis(String msg) {
        logger.info(msg);
        String[] msgInfo = msg.split("#");
        if(msgInfo[0].equals("glyw")){
            switch(msgInfo[1]){
                case "fireAct":
                    Fire_Act(msgInfo[2]);
                    break;
                case "setTemperature":
                    Set_Temperature(msgInfo[2]);

                default:
                    break;
            }
        }

    }

    private void Fire_Act(String num){

        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setInfCode("ntkt");
        cecDeviceInfoValue.setDevCode("1");
        switch (num)
        {
            case "1":
                cecDeviceInfoValue.setInfCode("0f_00");
                cecDeviceInfoValue.setCurrValue("1");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                break;
            case "2":
                cecDeviceInfoValue.setInfCode("16_1");
                cecDeviceInfoValue.setCurrValue("1");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                cecDeviceInfoValue.setInfCode("16_2");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                break;
            case "3":
                cecDeviceInfoValue.setInfCode("15");
                cecDeviceInfoValue.setCurrValue("1");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                break;
            case "4":
                cecDeviceInfoValue.setInfCode("0f_00");
                cecDeviceInfoValue.setCurrValue("0");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                cecDeviceInfoValue.setInfCode("16_1");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                cecDeviceInfoValue.setInfCode("16_2");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                cecDeviceInfoValue.setInfCode("15");
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
                break;
            default:
                break;

        }

    }

    private void Set_Temperature(String num)
    {
        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setComCode("glyw");
        cecDeviceInfoValue.setDevCode("1");
        cecDeviceInfoValue.setInfCode("set_temperature");
        cecDeviceInfoValue.setCurrValue(num);
        cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
    }


}
