package com.icbc.rel.hefei.dao.card;

import com.icbc.rel.hefei.entity.card.TaskInfo;

import java.util.List;

/**
 * @author LLF
 * @date 2019/10/12 - 13:56
 */
public interface TaskInfoDao {
    /**
     * 更新，根据id
     * @param taskInfo
     */
     void update(TaskInfo taskInfo);

    /**
     * 保存
     * @param taskInfo
     */
    void save(TaskInfo taskInfo);

    /**
     * 查询这个点要发送的卡片
     * @param currentTime
     * @return
     */
    List<TaskInfo> selectSendTaskInfo(String currentTime);

    void delete(Integer id);

    void deleteByTaskId(Integer taskId);
}
