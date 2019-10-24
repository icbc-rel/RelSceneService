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
 * ��ʱ���Ϳ�Ƭ
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
            System.out.println("��������������������");
            //��ѯ������
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TaskInfo taskInfo : taskInfos) {
                if (cardIdMap.get(taskInfo.getTaskId()) == null) {
                    //���TaskInfo��Ӧ�Ŀ�Ƭ
                    card = cardService.selectById(taskInfo.getTaskId(),taskInfo.getMpid());
                    cardIdMap.put(taskInfo.getTaskId(), card);
                }
                Date birthday = DateUtil.parse(format.format(taskInfo.getBirthday()));
                card = cardIdMap.get(taskInfo.getTaskId());
                //���ڵ�ʱ��
                Date nowDate = DateUtil.parse(DateUtil.now());
                //����ǽ��պؿ���������һ�꣬������
                if (card.getType() == 1) {
                    int nowYear = DateUtil.year(nowDate);
                    int birthdayYer = DateUtil.year(birthday);
                    if (nowYear != birthdayYer) {
                        continue;
                    }
                }
                int nowMonth = DateUtil.month(nowDate);
                int birthdayMouth = DateUtil.month(birthday);
                //�·ݲ�ͬ����
                if (nowMonth != birthdayMouth) {
                    continue;
                }
                //�����ǰ��Сʱ��������
                DateTime sendTime = DateUtil.offsetHour(birthday, card.getSendTime() - 13);
                int nowDay = Integer.parseInt(String.format("%td", nowDate));
                int birthdayDay = Integer.parseInt(String.format("%td", sendTime));
                //�ղ�ͬ����
                if (nowDay != birthdayDay) {
                    continue;
                }
                int nowHour = Integer.parseInt(String.format("%tH", nowDate));
                int birthdayHour = Integer.parseInt(String.format("%tH", sendTime));
                //Сʱ��ͬ
                if (nowHour != birthdayHour) {
                    continue;
                }
                //���÷���ʱ��
                taskInfo.setSendTime(DateUtil.offsetHour(nowDate, 13));
                //����ǽ��պؿ�����״̬Ϊ�����
                if (card.getType() == 1) {
                    card.setCondition((byte) 2);
                    cardIdMap.put(taskInfo.getTaskId(), card);
                    taskInfo.setIsDel((byte) 1);
                }
                IdWorker idWorker = new IdWorker();
                String cid = idWorker.nextId() + "";
                taskInfo.setCid(cid);
                //*��������*//*
                String mpId = taskInfo.getMpid();
                String mpUserPhone = taskInfo.getPhone();
                //0����ȫ����1��ȫ����Ĭ��Ϊ0
                String multiSend = "0";
                //* 0����e��ID 1��CIS��� 3���ֻ���*//*
                String channel = "3";
                //*  ͼ��Ϊnews����Ϊraw*//*
                String msgType = "raw";
                String content = "<a href=����/mycard?cid="+cid+">";
                //*��װ������*//*
                String finalXmlStr = anaylsisXmlUtil.makeXmlByHf005(mpId, multiSend, channel, mpUserPhone, msgType, content);
                //*����service������Ϣ��*//*
                //�����ݴ���redis��
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

