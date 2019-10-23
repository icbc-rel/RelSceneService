package com.icbc.rel.hefei.service.card.impl;

import com.icbc.rel.hefei.dao.card.TaskInfoDao;
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
 * @date 2019/10/12 - 13:57
 */
@Service
@Transactional
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoDao taskInfoDao;
    @Autowired
    private CardService cardService;

    @Override
    public void save(TaskInfo taskInfo) {
        taskInfoDao.save(taskInfo);
    }

    @Override
    public List<TaskInfo> selectSendTaskInfo(String currentTime) {
        return taskInfoDao.selectSendTaskInfo(currentTime);
    }

    @Override
    public void update(TaskInfo taskInfo) {
        taskInfoDao.update(taskInfo);
    }

    @Override
    public void delete(Integer id) {
        taskInfoDao.delete(id);
    }

    @Override
    public void deleteByTaskId(Integer taskId) {
        taskInfoDao.deleteByTaskId(taskId);
    }

    @Override
    public List<TaskInfo> selectByCardId(Integer id) {
        return taskInfoDao.selectByCardId(id);
    }

    @Override
    public Map<String, Integer> seleceSendCountByCardId(Integer id) {
        return taskInfoDao.seleceSendCountByCardId(id);
    }

    @Override
    public Map<String, Integer> selectViewCountByCardId(Integer id) {
        return taskInfoDao.selectViewCountByCardId(id);
    }

    @Override
    public TaskInfo selectByCid(String cid) {
       return taskInfoDao.selectByCid(cid);
    }

    @Override
    public List<TaskInfo> findAll() {
        return taskInfoDao.findAll();
    }

    @Override
    public List<TaskInfo> selectByPageAndCardId(Integer cardId, Integer limit, Integer page) {
        return taskInfoDao.selectByPageAndCardId(cardId,(page-1)*limit,limit);
    }

    @Override
    public Map<String, Integer> seleceCountByCardId(Integer cardId) {
        return taskInfoDao.seleceCountByCardId(cardId);
    }
}
