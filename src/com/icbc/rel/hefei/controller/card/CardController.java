package com.icbc.rel.hefei.controller.card;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.CardService;
import com.icbc.rel.hefei.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author LLF
 * @date 2019/10/12 - 9:15
 */
@RestController
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/card")
    public String save(@RequestBody Card card) {
        Map rtnMap = new HashMap<>(16);
        try {
            List<TaskInfo> taskInfos = new ArrayList<>(16);
            String mpId = SessionUtil.getMpId(request.getSession());
            card.setMpid(mpId);
            card.setCreateTime(DateUtil.date(System.currentTimeMillis()));
            card.setCondition((byte) 0);
            if (card.getTaskInfos() != null && card.getTaskInfos().size() > 0) {
                taskInfos = card.getTaskInfos();
                for (TaskInfo taskInfo : taskInfos) {
                    taskInfo.setMpid(mpId);
                    taskInfo.setIsDel((byte) 0);
                    //转为格式：yyyy-MM-dd HH:mm:ss
                    String oldTaskTime = DateUtil.formatDateTime(taskInfo.getBirthday());
                    Date date = DateUtil.parse(oldTaskTime);
                    Date taskTime = DateUtil.offsetHour(date, card.getSendTime() - 8);
                    //设置真正需要发送的时间
                    taskInfo.setTaskTime(taskTime);
                }
                card.setSendNum(taskInfos.size());
            }
            cardService.save(card, taskInfos);
            rtnMap.put("message", "添加成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("message", "添加失败,请重试");
            rtnMap.put("success", false);
        }
        return JSON.toJSONString(rtnMap);
    }

    @PutMapping("/card")
    public String update(@RequestBody Card card) {
        Map rtnMap = new HashMap<>(16);
        try {
            List<TaskInfo> taskInfos = new ArrayList<>(16);
            if (card.getTaskInfos() != null && card.getTaskInfos().size() > 0) {
                taskInfos = card.getTaskInfos();
                for (TaskInfo taskInfo : taskInfos) {
                    //转为格式：yyyy-MM-dd HH:mm:ss
                    String oldTaskTime = DateUtil.formatDateTime(taskInfo.getBirthday());
                    Date date = DateUtil.parse(oldTaskTime);
                    Date taskTime = DateUtil.offsetHour(date, card.getSendTime() - 8);
                    //设置真正需要发送的时间
                    taskInfo.setTaskTime(taskTime);
                }
                card.setSendNum(taskInfos.size());
            }
            cardService.update(card, taskInfos);
            rtnMap.put("message", "修改成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("message", "修改失败");
            rtnMap.put("success", false);
        }
        return JSON.toJSONString(rtnMap);
    }

    @DeleteMapping("/card")
    public String delete(@RequestBody Card card) {
        Map rtnMap = new HashMap<>(16);
        try {
            card.setCondition((byte) 1);
            cardService.delete(card.getId());
            rtnMap.put("message", "删除成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            rtnMap.put("message", "删除失败");
            rtnMap.put("success", false);
            e.printStackTrace();
        }
        return JSON.toJSONString(rtnMap);
    }
    //返回/card/cardconfig.jsp
    @GetMapping("/cardconfig")
    public ModelAndView  getCardConfig()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/cardconfig");
        return mav;
    }

    @GetMapping("/card")
    public ModelAndView  getTask()
    {
        List<Card> list=cardService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",list);
        mav.setViewName("card/task");
        return mav;
    }
}
