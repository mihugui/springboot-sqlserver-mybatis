package com.ibms.app.service.impl;

import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.dao.CecDeviceInfoValueMapper;
import com.ibms.app.service.ValueChanger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassNmae ValueChangeImpl
 * @Description TODO
 * @Author PWT
 * @Date 2018/11/14 10:50
 * @Version 1.0
 **/

@Service
public class ValueChangerImpl implements ValueChanger {

    @Autowired
    CecDeviceInfoValueMapper cecDeviceInfoValueMapper;

    protected Logger logger = LoggerFactory.getLogger(ValueChangerImpl.class);
    protected DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void ChangeTemperature() {

        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setComCode("ntkt");
        cecDeviceInfoValue.setDevCode("1");
        cecDeviceInfoValue.setInfCode("2.1");
        CecDeviceInfoValue inf_tem = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);
        cecDeviceInfoValue.setInfCode("14");
        CecDeviceInfoValue inf_air = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);

        if(inf_air.getCurrValue().equals("1"))
        {
            double value = Double.parseDouble(inf_tem.getCurrValue())-0.1;
            inf_tem.setCurrValue(df.format(value));
            logger.info("开始降温");
        }else{
            double value = Double.parseDouble(inf_tem.getCurrValue())+0.1;
            inf_tem.setCurrValue(df.format(value));
            logger.info("开始升温");
        }

        inf_tem.setCurrTime(new Date());
        cecDeviceInfoValueMapper.updateSelective(inf_tem);
    }

    @Override
    public void ChangeOther() {

        ChangeValue(80,50,"2.2");
        ChangeValue(60,40,"7");


    }

    @Override
    public void ChangeAirConditioner() {


        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setComCode("glyw");
        cecDeviceInfoValue.setDevCode("1");
        cecDeviceInfoValue.setInfCode("set_temperature");
        CecDeviceInfoValue info_settem = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);
        double tem = Double.parseDouble(info_settem.getCurrValue());

        cecDeviceInfoValue.setComCode("ntkt");
        cecDeviceInfoValue.setDevCode("1");
        cecDeviceInfoValue.setInfCode("2.1");
        CecDeviceInfoValue info_tem = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);
        cecDeviceInfoValue.setInfCode("14");
        CecDeviceInfoValue inf_air = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);
        if(Double.parseDouble(info_tem.getCurrValue())>(tem+1.5))
        {
            inf_air.setCurrValue("1");
            inf_air.setCurrTime(new Date());
            cecDeviceInfoValueMapper.updateSelective(inf_air);
            logger.info("开空调");
        }else if(Double.parseDouble(info_tem.getCurrValue())<(tem-1.5)){
            inf_air.setCurrValue("0");
            inf_air.setCurrTime(new Date());
            cecDeviceInfoValueMapper.updateSelective(inf_air);
            logger.info("关空调");
        }


    }

    private void ChangeValue(double max,double min,String inf_code){


        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setComCode("ntkt");
        cecDeviceInfoValue.setDevCode("1");
        cecDeviceInfoValue.setInfCode(inf_code);
        CecDeviceInfoValue info = cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).get(0);
        Random ra =new Random();

        if(Double.parseDouble(info.getCurrValue())>max)
        {
            double value = Double.parseDouble(info.getCurrValue())-ra.nextInt(10)*0.1;
            info.setCurrValue(df.format(value));
        }else if(Double.parseDouble(info.getCurrValue()) <min){
            double value = Double.parseDouble(info.getCurrValue())+ra.nextInt(10)*0.1;
            info.setCurrValue(df.format(value));
        }else{
            if(ra.nextInt(10)%2 == 0)
            {
                double value = Double.parseDouble(info.getCurrValue())+ra.nextInt(10)*0.1;
                info.setCurrValue(df.format(value));
            }else{
                double value = Double.parseDouble(info.getCurrValue())-ra.nextInt(10)*0.1;
                info.setCurrValue(df.format(value));
            }
        }

        info.setCurrTime(new Date());
        cecDeviceInfoValueMapper.updateSelective(info);

    }
}
