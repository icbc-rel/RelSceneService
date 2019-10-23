package com.icbc.rel.hefei.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SysMessageInfo {

    private Integer IID;
    private String mpId;//���ںű��
    private String activityUid;//����
    private String imUserId;//�û�id
    private String messageContent;//��������
    private Integer sendStatus;//����״̬
    private String returnMsg;//���ر���
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;//����ʱ��

    public Integer getIID() {
        return IID;
    }

    public void setIID(Integer iID) {
        IID = iID;
    }

    public String getActivityUid() {
        return activityUid;
    }

    public void setActivityUid(String activityUid) {
        this.activityUid = activityUid;
    }

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }
}
