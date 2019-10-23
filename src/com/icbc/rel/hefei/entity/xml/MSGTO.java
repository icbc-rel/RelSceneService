package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:15
 */
@XStreamAlias("MSGTO")
public class MSGTO {
    private String MULTISEND;
    private String CHANNEL;
    private String ID;

    public String getMULTISEND() {
        return MULTISEND;
    }

    public void setMULTISEND(String MULTISEND) {
        this.MULTISEND = MULTISEND;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
