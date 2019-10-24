package com.icbc.rel.hefei.service.card;

import com.icbc.rel.hefei.entity.card.TaskInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据id查
     * @param id
     * @return
     */
    List<TaskInfo> selectByCardId(Integer id);

    /**
     * 查询已发送数据条数
     * @param id
     * @return
     */
    Map<String, Integer> seleceSendCountByCardId(Integer id);

    /**
     * 查询已查看数据条数
     * @param id
     * @return
     */
    Map<String, Integer> selectViewCountByCardId(Integer id);

    /**
     * cid
     * @param cid
     * @return
     */
    TaskInfo selectByCid(String cid);

    /**
     * 查询所有
     * @return
     */
    List<TaskInfo> findAll();

    /**
     * 根据cardId分页查询
     * @param cardId
     * @param limit 几条
     * @param page 当前页码
     * @return
     */
    List<TaskInfo> selectByPageAndCardId(Integer cardId, Integer limit, Integer page);

    Map<String, Integer> seleceCountByCardId(Integer cardId);
}
