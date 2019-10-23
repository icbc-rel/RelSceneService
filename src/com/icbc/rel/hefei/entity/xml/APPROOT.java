package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:01
 */
@XStreamAlias("APPROOT")
public class APPROOT {
    private PUBLIC PUBLIC;
    private PRIVATE PRIVATE;

    public com.icbc.rel.hefei.entity.xml.PUBLIC getPUBLIC() {
        return PUBLIC;
    }

    public void setPUBLIC(com.icbc.rel.hefei.entity.xml.PUBLIC PUBLIC) {
        this.PUBLIC = PUBLIC;
    }

    public com.icbc.rel.hefei.entity.xml.PRIVATE getPRIVATE() {
        return PRIVATE;
    }

    public void setPRIVATE(com.icbc.rel.hefei.entity.xml.PRIVATE PRIVATE) {
        this.PRIVATE = PRIVATE;
    }
}
