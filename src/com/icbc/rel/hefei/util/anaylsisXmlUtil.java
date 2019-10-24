package com.icbc.rel.hefei.util;

import org.apache.log4j.Logger;
import org.dom4j.*;

import java.util.*;
import java.util.Map.Entry;

public class anaylsisXmlUtil {

	 private static Logger logger = Logger.getLogger(anaylsisXmlUtil.class);
	 public  Map getXml(String text) throws DocumentException {

	        Map<Object, Object> map = new HashMap<Object, Object>();
	        Document doc = null;
	        try {
	            doc = DocumentHelper.parseText(text);
	        } catch (DocumentException e) {
	            //����ʧ��
	            e.printStackTrace();
	        }
	        if(doc==null)
	            return map;
	        //��ȡ���ڵ�
	        Element element = doc.getRootElement();
	        //��ø��ڵ���������ֵ
	        List<?> iList = element.attributes();
	        for(int i=0;i<iList.size();i++){
	            Attribute attribute = (Attribute)iList.get(i);
	            map.put(attribute.getName(), attribute.getValue());
	        }

	        //�������ڵ�������Ϊproperty���ӽڵ�
	        Iterator<?> pIterator = element.elementIterator("FromUserName");//��ȡ��עʱ���openid
	        while(pIterator.hasNext()){
	            Element ele = (Element)pIterator.next();
	            //�ӽڵ��name��ֵ����Text
	            map.put("FromUserName", ele.getText());
	        }


	        Iterator<?> pIterator2 = element.elementIterator("MsgType");
	        while(pIterator2.hasNext()){
	            Element ele = (Element)pIterator2.next();
	            //�ӽڵ��name��ֵ����Text
	            map.put("MsgType", ele.getText());
	        }

	        String MsgTypeT=(String) map.get("MsgType");
	        logger.info("********"+MsgTypeT+"********");

	        if(MsgTypeT.equals("text")){

	        	  Iterator<?> pIterator4 = element.elementIterator("Content");//��ȡ��עʱ���openid
	              while(pIterator4.hasNext()){
	                  Element ele = (Element)pIterator4.next();
	                  //�ӽڵ��name��ֵ����Text
	                  map.put("Content", ele.getText());
	              }

	        }else if(MsgTypeT.equals("event")){

	        	 Iterator<?> pIterator1 = element.elementIterator("event");
	             while(pIterator1.hasNext()){
	                 Element ele = (Element)pIterator1.next();
	                 //�ӽڵ��name��ֵ����Text
	                 map.put("event", ele.getText());
	             }

	        }

	        Iterator<?> pIterator3 = element.elementIterator("CreateTime");//��ȡ��עʱ���openid
	        while(pIterator3.hasNext()){
	            Element ele = (Element)pIterator3.next();
	            //�ӽڵ��name��ֵ����Text
	            map.put("CreateTime", ele.getText());
	        }

	        //ѭ�����
	        Iterator<Entry<Object, Object>> iterator = map.entrySet().iterator();
	        while(iterator.hasNext()){
	            Entry<Object, Object> entry = iterator.next();
	            logger.info(entry.getKey()+":"+entry.getValue());
	        }
	        return map;
	    }



	 public String makeXmlByHf005(String Mpid,String multisend,String channel,String ID,String msgType,String content) throws DocumentException {

		    String date=DateUtil.getYDate();
		    String tm=DateUtil.tmToStr(new Date());
		    StringBuffer  xml=new StringBuffer();
		    xml.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		    xml.append("<APPROOT>");
		    xml.append("<PUBLIC>");
		    xml.append("<fcode>Y0001</fcode>");
		    xml.append("<server_ip>cjy</server_ip>");//
		    xml.append("<dt>"+date+"</dt>");
		    xml.append("<tm>"+tm+"</tm>");
		    xml.append("<channelIdentifier>cjy</channelIdentifier>");//
		    xml.append(" <channeLcODE>F-MIMS</channeLcODE>");
		    xml.append("<tm>"+tm+"</tm>");
		    xml.append("</PUBLIC>");
		    xml.append("<PRIVATE>");
		    xml.append(" <VERSION>1.0</VERSION>");
		    xml.append("<APPNAME>cjy</APPNAME>");//
		    xml.append("<MSGS>");
		    xml.append("<MSG>");
		    xml.append("<TRANSNO>���״���/��ˮ��</TRANSNO>");
		    xml.append(" <KEY>Ȩ����</KEY>");
		    xml.append(" <PRI>���ȼ�</PRI>");
		    xml.append("<MPID>"+Mpid+"</MPID> ");
		    xml.append(" <MSGTOS>");
		    xml.append("<<MSGTO>");
		    xml.append(" <MULTISEND>"+multisend+"</MULTISEND>");
		    xml.append("<CHANNEL>"+channel+"</CHANNEL>");
		    xml.append("<ID>"+ID+"</ID>");
		    xml.append("</MSGTO>");
		    xml.append(" </MSGTOS>");
		    xml.append("<SWITCH>���Կ���</SWITCH>>");
		    xml.append(" <PUSHSTR>������ʾ�ı�</PUSHSTR>");
		    xml.append(" <MSGTYPE>"+msgType+"</MSGTYPE>");
		    xml.append(" <CONTENT>"+content+"</CONTENT>  ");
		    xml.append(" <RESERVE>�����ֶ�</RESERVE>");
		    xml.append(" </MSG>");
		    xml.append(" </MSGS>");
		    xml.append(" </PRIVATE>");
		    xml.append(" </APPROOT>");

	        return xml.toString();
	    }


}
