package com.ibms.app.service;


public interface DataSave {

    void DataAnalysis(String msg);

    void SaveMJState(String ip,String State);

    void SaveMJCardID(String CardID);

    void SaveMJAlarmGeneral(String ip,String info);

    void SaveMJAlarmSerious(String ip,String info);

    void SaveMJAlarm(String ip,String info);
}
