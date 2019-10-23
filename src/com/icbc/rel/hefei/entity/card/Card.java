package com.icbc.rel.hefei.entity.card;

import java.util.Date;
import java.util.List;

public class Card {
    private Integer id;

    private String mpid;

    private Integer type;

    private String name;

    private String bg;

    private String writeName;

    private Integer sendNum;

    private Date createTime;

    private Byte condition;

    private Integer sendTime;

    private String wish;

    private List<TaskInfo> taskInfos;

    public List<TaskInfo> getTaskInfos() {
        return taskInfos;
    }

    public void setTaskInfos(List<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg == null ? null : bg.trim();
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName == null ? null : writeName.trim();
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getCondition() {
        return condition;
    }

    public void setCondition(Byte condition) {
        this.condition = condition;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish == null ? null : wish.trim();
    }
}
