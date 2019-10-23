package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:01
 */
@XStreamAlias("PUBLIC")
public class PUBLIC {
    private String FCODE;
    private String SERVER_IP;
    private String DT;
    private String TM;
    private String CHANNELIDENTIFIER;
    private String CHANNELCODE;

    public String getFCODE() {
        return FCODE;
    }

    public void setFCODE(String FCODE) {
        this.FCODE = FCODE;
    }

    public String getSERVER_IP() {
        return SERVER_IP;
    }

    public void setSERVER_IP(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    public String getDT() {
        return DT;
    }

    public void setDT(String DT) {
        this.DT = DT;
    }

    public String getTM() {
        return TM;
    }

    public void setTM(String TM) {
        this.TM = TM;
    }

    public String getCHANNELIDENTIFIER() {
        return CHANNELIDENTIFIER;
    }

    public void setCHANNELIDENTIFIER(String CHANNELIDENTIFIER) {
        this.CHANNELIDENTIFIER = CHANNELIDENTIFIER;
    }

    public String getCHANNELCODE() {
        return CHANNELCODE;
    }

    public void setCHANNELCODE(String CHANNELCODE) {
        this.CHANNELCODE = CHANNELCODE;
    }
}
