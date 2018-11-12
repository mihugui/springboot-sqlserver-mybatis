package com.ibms.app.service.impl;

import com.ibms.app.beans.CecAlarmHistory;
import com.ibms.app.beans.CecDeviceInfoValue;
import com.ibms.app.beans.CecDoorOpen;
import com.ibms.app.dao.CecAlarmHistoryMapper;
import com.ibms.app.dao.CecDeviceInfoValueMapper;
import com.ibms.app.dao.CecDoorOpenMapper;
import com.ibms.app.service.DataSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @ClassNmae DataSaveImpl
 * @Description TODO
 * @Author PWT
 * @Date 2018/11/9 8:53
 * @Version 1.0
 **/

@Service
public class DataSaveImpl implements DataSave {

    @Autowired
    CecDeviceInfoValueMapper cecDeviceInfoValueMapper;

    @Autowired
    CecDoorOpenMapper cecDeviceInfoValue;

    @Autowired
    CecAlarmHistoryMapper cecAlarmHistoryMapper;

    @Override
    public void DataAnalysis(String msg) {

        String[] msgInfo = msg.split("#");
        if(msgInfo[0].equals("mj*"))
        {
            if(msgInfo.length == 4)
            {
                String[] sendInfo = msgInfo[2].split(":");
                for(String info : sendInfo) {
                    String[] value = info.split("=");
                    SaveMJAlarm(value[0], "非法卡号" + value[2]);
                }
            }else{
                String[] sendInfo = msgInfo[2].split(":");

                for(String info : sendInfo)
                {
                    String[] value = info.split("=");
                    if(value[0].length() >5) {
                        if(value[1].equals("2"))
                        {
                            if(value[2].equals("2")){
                                SaveMJAlarmGeneral(value[0],"门禁未关(超时)报警");
                            }else{
                                SaveMJAlarmSerious(value[0],"机箱入侵(防拆)报警");
                            }
                        }else{
                            SaveMJState(value[0],value[2]);
                            System.out.println("门状态");
                        }

                    }else{
                        SaveMJCardID(value[2]);
                        System.out.println("刷卡记录");
                    }
                }
            }
        }else{


        }

    }

    @Override
    public void SaveMJState(String ip, String State) {
        CecDeviceInfoValue cecDeviceInfoValue = new CecDeviceInfoValue();
        cecDeviceInfoValue.setComCode("mj");
        cecDeviceInfoValue.setCurrValue(State);
        cecDeviceInfoValue.setInfCode("1");
        cecDeviceInfoValue.setDevCode(ip);
        cecDeviceInfoValue.setCurrTime( new Date());


        try {
            if(cecDeviceInfoValueMapper.selectByBean(cecDeviceInfoValue).size() != 0)
            {
                cecDeviceInfoValueMapper.updateSelective(cecDeviceInfoValue);
            }else{
                cecDeviceInfoValueMapper.insertSelective(cecDeviceInfoValue);
            }

            System.out.println("门禁状态更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SaveMJCardID(String CardID) {
        CecDoorOpen cecDoorOpen = new CecDoorOpen();
        cecDoorOpen.setCardid(CardID);
        cecDoorOpen.setOpendate(new Date());
        try {
            cecDeviceInfoValue.insertSelective(cecDoorOpen);
            System.out.println("开门数据更新");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SaveMJAlarmGeneral(String ip, String info) {
        CecAlarmHistory cecAlarmHistory = new CecAlarmHistory();
        cecAlarmHistory.setAlarmTime(new Date());
        cecAlarmHistory.setComCode("mj");
        cecAlarmHistory.setDevCode(ip);
        cecAlarmHistory.setInfCode("1");
        cecAlarmHistory.setValue("1");
        cecAlarmHistory.setLevelCode("3");
        cecAlarmHistory.setAlarmDesc(info);
        cecAlarmHistory.setClassifyCode("1");
        cecAlarmHistory.setLocation("");

        List<CecAlarmHistory> list_alarm = cecAlarmHistoryMapper.selectByBean(cecAlarmHistory);

        if(list_alarm.size() != 0)
        {
            System.out.println("已经存在相同的报警，请等待处理");

        }else{
            System.out.println("报警插入成功");
            cecAlarmHistoryMapper.insertSelective(cecAlarmHistory);
        }
    }

    @Override
    public void SaveMJAlarmSerious(String ip, String info) {
        CecAlarmHistory cecAlarmHistory = new CecAlarmHistory();
        cecAlarmHistory.setAlarmTime(new Date());
        cecAlarmHistory.setComCode("mj");
        cecAlarmHistory.setDevCode(ip);
        cecAlarmHistory.setInfCode("1");
        cecAlarmHistory.setValue("1");
        cecAlarmHistory.setLevelCode("1");
        cecAlarmHistory.setAlarmDesc(info);
        cecAlarmHistory.setClassifyCode("1");
        cecAlarmHistory.setLocation("");

        List<CecAlarmHistory> list_alarm = cecAlarmHistoryMapper.selectByBean(cecAlarmHistory);

        if(list_alarm.size() != 0)
        {
            System.out.println("已经存在相同的报警，请等待处理");

        }else{
            System.out.println("报警插入成功");
            cecAlarmHistoryMapper.insertSelective(cecAlarmHistory);
        }
    }

    @Override
    public void SaveMJAlarm(String ip, String info) {
        CecAlarmHistory cecAlarmHistory = new CecAlarmHistory();
        cecAlarmHistory.setComCode("mj");
        cecAlarmHistory.setDevCode(ip);
        cecAlarmHistory.setInfCode("1");
        cecAlarmHistory.setValue("1");
        cecAlarmHistory.setLevelCode("2");
        cecAlarmHistory.setAlarmDesc(info);
        cecAlarmHistory.setClassifyCode("1");
        cecAlarmHistory.setLocation("");
        cecAlarmHistory.setAlarmTime(new Date());

        List<CecAlarmHistory> list_alarm = cecAlarmHistoryMapper.selectByBean(cecAlarmHistory);

        if(list_alarm.size() != 0)
        {
            System.out.println("已经存在相同的报警，请等待处理");
        }else{
            System.out.println("报警插入成功");
            cecAlarmHistoryMapper.insertSelective(cecAlarmHistory);
        }
    }

}
