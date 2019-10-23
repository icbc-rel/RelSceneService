package com.icbc.rel.hefei.dao.card;

import com.icbc.rel.hefei.entity.card.Card;

import java.util.List;

/**
 * @author LLF
 * @date 2019/10/12 - 9:17
 */

public interface CardDao {

    /**
     * 保存
     * @param card
     */
    Integer save(Card card);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Card selectById(Integer id);

    /**
     * 更新
     * @param card
     */
    void update(Card card);

    /**
     * 逻辑上删除，将condition置为1
     * @param id
     */
    void delete(Integer id);

    List<Card> findAll();
}
