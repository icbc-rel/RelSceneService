package com.icbc.rel.hefei.service.card;

import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.TaskInfo;

import java.util.List;
import java.util.Map;

/**
 * @author LLF
 * @date 2019/10/12 - 9:17
 */
public interface CardService {


    /**
     * 保存
     *
     * @param card 贺卡
     * @param taskInfos 列表
     */
    void save(Card card, List<TaskInfo> taskInfos);

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    Card selectById(Integer id,String mpId);

    /**
     * job完成后修改专用
     * @param cardMap
     * @param taskInfos
     */
    void update(Map<Integer, Card> cardMap, List<TaskInfo> taskInfos);

    /**
     * 修改
     * @param card
     *
     */
    void update(Card card);

    /**
     * 逻辑上删除，实际数据库还有
     * @param id
     */
    void delete(Integer id,String mpid);

    /**
     * 查询所有
     * @return
     */
    List<Card> findAll(String mpId);

}
