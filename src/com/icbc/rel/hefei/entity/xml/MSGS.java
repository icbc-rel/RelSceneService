package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:04
 */
@XStreamAlias("MSGS")
public class MSGS {
    private MSG MSG;

    public com.icbc.rel.hefei.entity.xml.MSG getMSG() {
        return MSG;
    }

    public void setMSG(com.icbc.rel.hefei.entity.xml.MSG MSG) {
        this.MSG = MSG;
    }
}
