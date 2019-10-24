package com.icbc.rel.hefei.service.card.impl;

import com.icbc.rel.hefei.dao.card.CardDao;
import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.CardService;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LLF
 * @date 2019/10/12 - 9:18
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;
    @Autowired
    private TaskInfoService taskInfoService;


    @Override
    public void save(Card card, List<TaskInfo> taskInfos) {
        cardDao.save(card);
        for (TaskInfo taskInfo : taskInfos) {
            taskInfo.setTaskId(card.getId());
            taskInfoService.save(taskInfo);
        }
    }
    @Override
    public Card selectById(Integer id,String mpId) {
        return cardDao.selectById(id,mpId);
    }

    @Override
    public void update(Map<Integer, Card> cardMap, List<TaskInfo> taskInfos) {
        Card card=null;
        for (Integer integer : cardMap.keySet()) {
            card=cardMap.get(integer);
            cardDao.update(card);
        }
        for (TaskInfo taskInfo : taskInfos) {
            System.out.println(taskInfo.getSendTime());
            taskInfoService.update(taskInfo);
        }
    }

    @Override
    public void update(Card card) {
        cardDao.update(card);
    }

   /* @Override
    public void update(Card card, List<TaskInfo> taskInfos) {
        cardDao.update(card);
        for (TaskInfo taskInfo : taskInfos) {
            taskInfoService.update(taskInfo);
        }
    }*/

    @Override
    public void delete(Integer id,String mpid) {
        cardDao.delete(id,mpid);
        taskInfoService.deleteByTaskId(id);
    }

    @Override
    public List<Card> findAll(String mpId) {
        return cardDao.findAll(mpId);
    }

}
