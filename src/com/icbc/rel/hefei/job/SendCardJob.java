package com.icbc.rel.hefei.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.IdWorker;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.CardService;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import com.icbc.rel.hefei.service.sys.MessageService;
import com.icbc.rel.hefei.util.anaylsisXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时发送卡片
 *
 * @author LLF
 * @date 2019/10/12 - 9:46
 */
@Component
public class SendCardJob {
    @Autowired
    private CardService cardService;
    @Autowired
    private TaskInfoService taskInfoService;
   /* @Autowired
    private RedisTemplate redisTemplate;*/
    public void send() {
        Card card = null;
        String activityId = null;
        List<TaskInfo> taskInfos = null;
        Map<Integer, Card> cardIdMap = new HashMap(16);
        try {
            taskInfos = taskInfoService.findAll();
            anaylsisXmlUtil anaylsisXmlUtil = new anaylsisXmlUtil();
            System.out.println("任务启动。。。。。。");
            //查询出所有
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TaskInfo taskInfo : taskInfos) {
                if (cardIdMap.get(taskInfo.getTaskId()) == null) {
                    //查出TaskInfo对应的卡片
                    card = cardService.selectById(taskInfo.getTaskId(),taskInfo.getMpid());
                    cardIdMap.put(taskInfo.getTaskId(), card);
                }
                Date birthday = DateUtil.parse(format.format(taskInfo.getBirthday()));
                card = cardIdMap.get(taskInfo.getTaskId());
                //现在的时间
                Date nowDate = DateUtil.parse(DateUtil.now());
                //如果是节日贺卡，不是这一年，则跳过
                if (card.getType() == 1) {
                    int nowYear = DateUtil.year(nowDate);
                    int birthdayYer = DateUtil.year(birthday);
                    if (nowYear != birthdayYer) {
                        continue;
                    }
                }
                int nowMonth = DateUtil.month(nowDate);
                int birthdayMouth = DateUtil.month(birthday);
                //月份不同跳过
                if (nowMonth != birthdayMouth) {
                    continue;
                }
                //算出提前几小时发的日期
                DateTime sendTime = DateUtil.offsetHour(birthday, card.getSendTime() - 13);
                int nowDay = Integer.parseInt(String.format("%td", nowDate));
                int birthdayDay = Integer.parseInt(String.format("%td", sendTime));
                //日不同跳过
                if (nowDay != birthdayDay) {
                    continue;
                }
                int nowHour = Integer.parseInt(String.format("%tH", nowDate));
                int birthdayHour = Integer.parseInt(String.format("%tH", sendTime));
                //小时不同
                if (nowHour != birthdayHour) {
                    continue;
                }
                //设置发送时间
                taskInfo.setSendTime(DateUtil.offsetHour(nowDate, 13));
                //如果是节日贺卡设置状态为已完成
                if (card.getType() == 1) {
                    card.setCondition((byte) 2);
                    cardIdMap.put(taskInfo.getTaskId(), card);
                    taskInfo.setIsDel((byte) 1);
                }
                IdWorker idWorker = new IdWorker();
                String cid = idWorker.nextId() + "";
                taskInfo.setCid(cid);
                //*发送设置*//*
                String mpId = taskInfo.getMpid();
                String mpUserPhone = taskInfo.getPhone();
                //0：非全发；1：全发（默认为0
                String multiSend = "0";
                //* 0：融e联ID 1：CIS编号 3：手机号*//*
                String channel = "3";
                //*  图文为news其它为raw*//*
                String msgType = "raw";
                String content = "<a href=链接/mycard?cid="+cid+">";
                //*组装发送体*//*
                String finalXmlStr = anaylsisXmlUtil.makeXmlByHf005(mpId, multiSend, channel, mpUserPhone, msgType, content);
                //*调用service发送消息体*//*
                //将数据存入redis中
               /* redisTemplate.opsForValue().set(cid+"taskinfo",taskInfo,2, TimeUnit.HOURS);
                redisTemplate.opsForValue().set(cid+"card",card,2,TimeUnit.HOURS);*/
                Boolean isSend = MessageService.sendRtfByHf500(finalXmlStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cardService.update(cardIdMap, taskInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

