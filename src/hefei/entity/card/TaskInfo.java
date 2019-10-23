package com.icbc.rel.hefei.entity.card;

import java.io.Serializable;
import java.util.Date;

public class TaskInfo implements Serializable {
    private Integer id;

    private String mpid;
    /**
     * 对应贺卡id
     */
    private Integer taskId;
    /**
     * 电话
     */
    private String phone;
    /**
     * 姓名
     */
    private String cusName;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 查看时间
     */
    private Date viewTime;

    private Byte isDel;
    /**
     * 保留字段。。
     */
    private Date taskTime;
    /**
     * 日期，当为生日贺卡时是生日，为节日贺卡是为节日日期
     */
    private Date birthday;
    /**
     * 链接cid
     */
    private String cid;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
