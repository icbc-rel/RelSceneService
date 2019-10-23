package com.icbc.rel.hefei.job;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.RtfContent;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.CardService;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import com.icbc.rel.hefei.service.sys.SysMassageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private SysMassageInfoService sysMassageInfoService;

    public void send() {
        System.out.println("任务启动。。。。。。");
        //当前时间
        //查询出所有这个时段要发送的任务
        List<TaskInfo> taskInfos = taskInfoService.selectSendTaskInfo(DateUtil.now());
        Map<Integer, Card> cardIdMap = new HashMap(16);
        Card card = null;
        RtfContent rtfContent = null;
        String activityId = null;
        for (TaskInfo taskInfo : taskInfos) {
            if (cardIdMap.get(taskInfo.getTaskId()) == null) {
                //查出TaskInfo对应的卡片
                card = cardService.selectById(taskInfo.getTaskId());
                cardIdMap.put(taskInfo.getTaskId(), card);
            }
            Date date = DateUtil.parse(DateUtil.now());
            Date sendTime = DateUtil.offsetHour(date, 13);
            //设置发送时间
            taskInfo.setSendTime(sendTime);
            //如果是节日贺卡设置状态为已完成
            if (cardIdMap.get(taskInfo.getTaskId()).getType() == 1) {
                card = cardIdMap.get(taskInfo.getTaskId());
                card.setCondition((byte) 2);
                cardIdMap.put(taskInfo.getTaskId(), card);
                taskInfo.setIsDel((byte) 1);
            }
            card = cardIdMap.get(taskInfo.getTaskId());
            rtfContent = new RtfContent(card.getName(), card.getBg(), card.getWish(), card.getWriteName());
            sysMassageInfoService.sendRtfByHf500("", activityId, taskInfo.getPhone(), JSON.toJSONString(card));
        }
        cardService.update(cardIdMap, taskInfos);
    }

}

