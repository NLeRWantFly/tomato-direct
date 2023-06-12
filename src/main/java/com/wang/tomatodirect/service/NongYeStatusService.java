package com.wang.tomatodirect.service;

import cn.easyes.core.conditions.select.LambdaEsQueryWrapper;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.wang.tomatodirect.dao.NongYeStatusDao;
import com.wang.tomatodirect.pojo.NongYeStatusPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author NLER
 * @date 2023/6/12 22:06
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class NongYeStatusService {

    @Autowired
    NongYeStatusDao nongYeStatusDao;

    public void insertNongYeStatus(String temp,String humi,String intensity){
        nongYeStatusDao.insert(new NongYeStatusPojo(null,temp,humi,intensity, DateUtil.today()));
        log.info("insert a new Data: temp={},humi={},intensity={},Data={}",temp,humi,intensity,DateUtil.today());
    }

    // 查询昨天24小时的数据
    public List<NongYeStatusPojo> find7Day24HourData(){
        // 24小时之首
        String date = DateUtil.today();
        // 今天
        Date todayDate = DateUtil.parse(date);
        Date todayDateFirst = DateUtil.beginOfDay(todayDate);
        Date todayDateEnd = DateUtil.endOfDay(todayDate);
        // 昨天
        Date yesterdayDate = DateUtil.yesterday();
        Date yesterdayFirst = DateUtil.beginOfDay(yesterdayDate);
        Date yesterdayEnd = DateUtil.endOfDay(yesterdayDate);

        List<NongYeStatusPojo> list = nongYeStatusDao.selectList(new LambdaEsQueryWrapper<NongYeStatusPojo>()
                .ge("createDate", yesterdayFirst).lt("createDate", todayDateFirst));
        return list;
    }

    // 查询之前某一天几天的数据（用偏移量）
    public List<NongYeStatusPojo> findDayOffsetNum(Integer num){
        // 今天
        String date = DateUtil.today();
        // 今天
        Date todayDate = DateUtil.parse(date);
        todayDate = DateUtil.offset(todayDate, DateField.DAY_OF_MONTH, num-1);
        Date newDate = DateUtil.offset(todayDate, DateField.DAY_OF_MONTH, num);

        Date todayDateFirst = DateUtil.beginOfDay(todayDate);
        Date todayDateEnd = DateUtil.endOfDay(todayDate);
        // 昨天
        Date yesterdayDate = DateUtil.yesterday();
        Date yesterdayFirst = DateUtil.beginOfDay(yesterdayDate);
        Date yesterdayEnd = DateUtil.endOfDay(yesterdayDate);

        List<NongYeStatusPojo> list = nongYeStatusDao.selectList(new LambdaEsQueryWrapper<NongYeStatusPojo>()
                .gt("createDate", yesterdayFirst).le("createDate", todayDateFirst));
        return list;
    }
}
