package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:06
 */
@XStreamAlias("MSG")
public class MSG {
    private String TRANSNO;
    private String KEY;
    private String PRI;
    private String MPID;
    private MSGTOS MSGTOS;
    private String SWITCH;
    private String PUSHSTR;
    private String MSGTYPE;
    private String CONTENT;
    private String RESERVE;

    public String getTRANSNO() {
        return TRANSNO;
    }

    public void setTRANSNO(String TRANSNO) {
        this.TRANSNO = TRANSNO;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public String getPRI() {
        return PRI;
    }

    public void setPRI(String PRI) {
        this.PRI = PRI;
    }

    public String getMPID() {
        return MPID;
    }

    public void setMPID(String MPID) {
        this.MPID = MPID;
    }

    public com.icbc.rel.hefei.entity.xml.MSGTOS getMSGTOS() {
        return MSGTOS;
    }

    public void setMSGTOS(com.icbc.rel.hefei.entity.xml.MSGTOS MSGTOS) {
        this.MSGTOS = MSGTOS;
    }

    public String getSWITCH() {
        return SWITCH;
    }

    public void setSWITCH(String SWITCH) {
        this.SWITCH = SWITCH;
    }

    public String getPUSHSTR() {
        return PUSHSTR;
    }

    public void setPUSHSTR(String PUSHSTR) {
        this.PUSHSTR = PUSHSTR;
    }

    public String getMSGTYPE() {
        return MSGTYPE;
    }

    public void setMSGTYPE(String MSGTYPE) {
        this.MSGTYPE = MSGTYPE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getRESERVE() {
        return RESERVE;
    }

    public void setRESERVE(String RESERVE) {
        this.RESERVE = RESERVE;
    }
}
