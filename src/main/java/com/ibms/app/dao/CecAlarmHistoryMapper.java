package com.ibms.app.dao;

import com.ibms.app.beans.CecAlarmHistory;

import java.util.List;

public interface CecAlarmHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CEC_ALARM_HISTORY
     *
     * @mbggenerated
     */
    int insert(CecAlarmHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CEC_ALARM_HISTORY
     *
     * @mbggenerated
     */
    int insertSelective(CecAlarmHistory record);

    List<CecAlarmHistory> selectByBean(CecAlarmHistory cecAlarmHistory);
}