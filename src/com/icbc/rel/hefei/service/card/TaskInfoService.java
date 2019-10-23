package com.icbc.rel.hefei.service.card;

import com.icbc.rel.hefei.entity.card.TaskInfo;

import java.util.List;

/**
 * @author LLF
 * @date 2019/10/12 - 13:57
 */
public interface TaskInfoService {

    /**
     * 保存
     *
     * @param taskInfo
     */
    void save(TaskInfo taskInfo);

    /**
     * 查询这个点要发送的卡片
     *
     * @param currentTime
     * @return
     */
    List<TaskInfo> selectSendTaskInfo(String currentTime);

    /**
     * 根据id更新
     * @param taskInfo
     */
    void update(TaskInfo taskInfo);

    void delete(Integer id);

    /**
     * 逻辑删除
     * @param taskId
     */
    void deleteByTaskId(Integer taskId);
}
