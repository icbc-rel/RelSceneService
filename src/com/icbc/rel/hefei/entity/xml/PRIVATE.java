package com.icbc.rel.hefei.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author LLF
 * @date 2019/10/15 - 16:03
 */
@XStreamAlias("PRIVATE")
public class PRIVATE {
    private String VERSION;
    private String APPNAME;
    private MSGS MSGS;

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getAPPNAME() {
        return APPNAME;
    }

    public void setAPPNAME(String APPNAME) {
        this.APPNAME = APPNAME;
    }

    public com.icbc.rel.hefei.entity.xml.MSGS getMSGS() {
        return MSGS;
    }

    public void setMSGS(com.icbc.rel.hefei.entity.xml.MSGS MSGS) {
        this.MSGS = MSGS;
    }
}
