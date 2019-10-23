package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:15
 */
@XStreamAlias("MSGTOS")
public class MSGTOS {
    private MSGTO MSGTO;

    public com.icbc.rel.hefei.entity.xml.MSGTO getMSGTO() {
        return MSGTO;
    }

    public void setMSGTO(com.icbc.rel.hefei.entity.xml.MSGTO MSGTO) {
        this.MSGTO = MSGTO;
    }
}
