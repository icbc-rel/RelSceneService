package com.icbc.rel.hefei.controller.card;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.icbc.rel.hefei.entity.card.Card;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.CardService;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import com.icbc.rel.hefei.service.sys.ImUserService;
import com.icbc.rel.hefei.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 保存
     *
     * @param card
     * @param request
     * @return
     */
    @PostMapping("/card")
    public String save(Card card, HttpServletRequest request) {
        Map rtnMap = new HashMap<>(16);
        try {
            HttpSession httpSession = request.getSession();
            List<TaskInfo> taskInfos = new ArrayList<>(16);
            String mpId = SessionUtil.getMpId(request.getSession());
            if(mpId==null|| mpId.equals(""))
            {
                throw new  NullPointerException();
            }
            card.setMpid(mpId);
            card.setCreateTime(DateUtil.date(System.currentTimeMillis()));
            card.setCondition((byte) 0);
            card.setName(card.getName().trim());
            if (httpSession.getAttribute("taskInfos") != null) {
                taskInfos = (List<TaskInfo>) httpSession.getAttribute("taskInfos");
                for (TaskInfo taskInfo : taskInfos) {
                    taskInfo.setMpid(mpId);
                    taskInfo.setIsDel((byte) 0);
                }
                card.setSendNum(taskInfos.size());
            }
            cardService.save(card, taskInfos);
            httpSession.removeAttribute("taskInfos");
            rtnMap.put("message", "添加成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("message", "添加失败,请重试");
            rtnMap.put("success", false);
        }
        return JSON.toJSONString(rtnMap);
    }

    /**
     * 修改
     *
     * @param card
     * @return
     */
    @PostMapping("/card/{id}")
    public String update(Card card, @PathVariable Integer id) {
        Map rtnMap = new HashMap(2);
        try {
            String mpId = SessionUtil.getMpId(request.getSession());
            if(mpId==null|| mpId.equals(""))
            {
                throw new  NullPointerException();
            }
            Card updateCard = cardService.selectById(id,mpId);
            updateCard.setName(card.getName());
            updateCard.setType(card.getType());
            updateCard.setWish(card.getWish());
            updateCard.setWriteName(card.getWriteName());
            updateCard.setBg(card.getBg());
            updateCard.setSendTime(card.getSendTime());
            cardService.update(updateCard);
            rtnMap.put("message", "修改成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            rtnMap.put("message", "修改失败,请重试");
            rtnMap.put("success", false);
            e.printStackTrace();
        }
        return JSON.toJSONString(rtnMap);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/card/delete/{id}")
    public Map delete(@PathVariable("id") Integer id) {
        Map rtnMap = new HashMap<>(16);
        try {
            String mpId = SessionUtil.getMpId(request.getSession());
            if(mpId==null|| mpId.equals(""))
            {
                throw new  NullPointerException();
            }
            cardService.delete(id,mpId);
            rtnMap.put("code", 0);
        } catch (Exception e) {
            rtnMap.put("code", "1");
            e.printStackTrace();
        }
        return rtnMap;
    }

    @GetMapping("/cardconfig")
    public ModelAndView getCardConfig() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/cardconfig");
        return mav;
    }

    @GetMapping("/task")
    public ModelAndView getTask() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card/task");
        return mav;
    }

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping("/card")
    public Map getCard() {
        Map rtnMap = new HashMap(16);
        try {
            String mpId = SessionUtil.getMpId(request.getSession());
            if(mpId==null|| mpId.equals(""))
            {
                throw new NullPointerException();
            }

            List<Card> list = cardService.findAll(mpId);
            rtnMap.put("code", 0);
            rtnMap.put("data", list);
            return rtnMap;
        } catch (Exception e) {
            rtnMap.put("code", 0);
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @GetMapping("/card/{id}")
    public Map getCard(@PathVariable Integer id) {
        Map rtnMap = null;
        try {
            String mpId = SessionUtil.getMpId(request.getSession());
            if(mpId==null|| mpId.equals(""))
            {
                throw new  NullPointerException();
            }
            Card card = cardService.selectById(id,mpId);
            rtnMap = new HashMap(16);
            rtnMap.put("code", 0);
            rtnMap.put("data", card);
            Map<String, Integer> sendCount = taskInfoService.seleceSendCountByCardId(id);
            Map<String, Integer> viewCount = taskInfoService.selectViewCountByCardId(id);
            rtnMap.put("sendCount", sendCount.get("sendCount"));
            rtnMap.put("viewCount", viewCount.get("viewCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * 统计界面
     *
     * @param id
     * @return
     */
    @GetMapping("/analysis/{id}")
    public ModelAndView getAnalysis(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("card/analysis");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    /**
     * 查看配置界面
     *
     * @param id
     * @return
     */
    @GetMapping("/updatecardconfig")
    public ModelAndView getCardConfig(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("card/cardconfig");
        String mpId = SessionUtil.getMpId(request.getSession());
        modelAndView.addObject("card", cardService.selectById(id,mpId));
        return modelAndView;
    }
    /**
     *c端卡片页面
     * @param cid
     * @return
     */
    @GetMapping("/mycard")
    public ModelAndView getMyCard(String cid) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            TaskInfo taskInfo = taskInfoService.selectByCid(cid);
            //头像
            String portrait = ImUserService.FetchUserInfoByMobileNo(taskInfo.getPhone()).getPortrait();
            modelAndView.addObject("url", portrait);
            Card card = cardService.selectById(taskInfo.getTaskId(),taskInfo.getMpid());
            modelAndView.setViewName("card/mycard");
            modelAndView.addObject("card", card);
            String sendTime=String.format("%tY-%<tm-%<td",DateUtil.offsetHour(taskInfo.getSendTime(),  - 13));
            modelAndView.addObject("sendTime",sendTime);
            taskInfo.setViewTime(DateUtil.offsetHour(new Date(), 13));
            taskInfoService.update(taskInfo);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
