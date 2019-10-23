package com.icbc.rel.hefei.service.sys;

import cn.hutool.core.date.DateUtil;
import com.icbc.rel.hefei.dao.SysMessageInfoDao;
import com.icbc.rel.hefei.entity.SysMessageInfo;
import com.icbc.rel.hefei.entity.xml.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class SysMassageInfoService {

    @Autowired
    private SysMessageInfoDao dao;

    public void insert(SysMessageInfo info) {
        dao.insert(info);
    }

    public List<SysMessageInfo> queryInfo(String activityUid, int page, int limit) {
        return dao.queryInfo(activityUid, (page - 1) * limit, limit);
    }

    //?????????????
    public int queryInfoNum(String activityUid) {
        List<Integer> result = dao.queryInfoNum(activityUid);
        if (result != null && result.size() > 0) {
            return result.get(0) == null ? 0 : result.get(0);
        } else {
            return 0;
        }
    }

    /**
     * 使用 HF500 接口发送
     * @param mpId 公众号id
     * @param activityId 活动id
     * @param receivePhoneId 接收者手机号
     * @param rtfContent 富文本json字符串
     * @return
     */
    public boolean sendRtfByHf500(String mpId, String activityId, String receivePhoneId, String rtfContent) {
        System.out.println("发送xml......");
        APPROOT approot = new APPROOT();
        MSG msg = new MSG();
        MSGS msgs = new MSGS();
        MSGTO msgto = new MSGTO();
        MSGTOS msgtos = new MSGTOS();
        PRIVATE aPrivate = new PRIVATE();
        PUBLIC aPublic = new PUBLIC();
        msgto.setCHANNEL("3");
        msgto.setID(receivePhoneId);
        msgto.setMULTISEND("0");
        msgtos.setMSGTO(msgto);
        msg.setMSGTOS(msgtos);
        msg.setKEY("");
        msg.setPRI("0");
        msg.setMPID(mpId);
        msg.setSWITCH("0");
        try {
            msg.setPUSHSTR(new String("您收到一条新贺卡".getBytes("UTF-8")));
            msg.setCONTENT(new String(rtfContent.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msg.setMSGTYPE("1");
        msg.setRESERVE("");
        msg.setTRANSNO("");
        msgs.setMSG(msg);
        aPrivate.setMSGS(msgs);
        //占位
        aPrivate.setAPPNAME("CardSender");
        aPrivate.setVERSION("1.0");
        aPublic.setFCODE("Y0001");
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        aPublic.setSERVER_IP(addr.getHostAddress());
        aPublic.setDT(DateUtil.today());
        aPublic.setTM(DateUtil.format(DateUtil.date(), "HHmmss"));
        aPublic.setCHANNELIDENTIFIER("test");
        aPublic.setCHANNELCODE("test");
        approot.setPRIVATE(aPrivate);
        approot.setPUBLIC(aPublic);
        XStream xStream = null;
        try {
            xStream = new XStream(new Xpp3Driver(new NoNameCoder()));
            xStream.alias(approot.getClass().getSimpleName(), approot.getClass());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String sendString = "<?XML VERSION=\"1.0\" ENCODING=\"GBK\"?>\n" + xStream.toXML(approot);
            System.out.println(new String(sendString.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.format(DateUtil.date(), "HHmmss"));
    }
}
