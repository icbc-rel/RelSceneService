package com.icbc.rel.hefei.service.card.impl;

import com.icbc.rel.hefei.dao.card.TaskInfoDao;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LLF
 * @date 2019/10/12 - 13:57
 */
@Service
@Transactional
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoDao taskInfoDao;


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

}
