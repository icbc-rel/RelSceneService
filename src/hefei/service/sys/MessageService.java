package com.icbc.rel.hefei.service.sys;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.icbc.rel.hefei.util.SystemConfigUtil;
import com.icbc.rel.hefei.util.anaylsisXmlUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * ��e����Ϣ���ͽӿڷ���
 * H0002
 */
@Service
public class MessageService {
	private static final Logger logger = Logger.getLogger(MessageService.class);

	/*
	 * ��ָ����e���û�������Ϣ���ı���ͼ�ģ�
	 * @param imUserId ��e��id
	 * @result 1/0
	 */
	public static boolean SendMessage(JSONObject msgBody) {
		String returnstr = null;// ���ؽ�����ַ���
		String uuid=UUID.randomUUID().toString().replace("-", "");
		try {

			String apiUrl= SystemConfigUtil.apiUrl;
//				String url = apiUrl+"ICBCMPServer/servlet/ThirdPartyServlet?Channel=IM&fCode=HF002&msgFrom="
//						+URLEncoder.encode(mpid, "utf-8")
//						+"&msgToIMUserId="
//						+ URLEncoder.encode(imUserId, "utf-8")
//						+"&in_content="
//						+URLEncoder.encode(msg, "utf-8")
//						+"&in_msgid="
//						+URLEncoder.encode(uuid, "utf-8") ;
				String url = apiUrl+"IMServiceServer/servlet/ThirdPartyServlet?Channel=IM&fCode=HF002&msgBody="+URLEncoder.encode(msgBody.toString(), "utf-8");
				logger.info("������Ϣurl"+url);
				HttpGet httpget = new HttpGet(url);
				HttpClient client = new DefaultHttpClient();
				HttpResponse result = client.execute(httpget);
				byte[] returnByte=IOUtils.toByteArray(result.getEntity().getContent());
				returnstr=new String(returnByte,"utf-8");
				logger.info("return:" + returnstr);
			    if (returnstr == null || returnstr.equals("")) {
				  return false;
			    }
			    returnstr=URLDecoder.decode(returnstr,"utf-8");
				JSONObject jsonret = JSONObject.parseObject(returnstr);
				JSONObject err=jsonret.getJSONObject("PUBLIC");
				if(err.getString("RETCODE").trim().equals("0000")) {
					logger.info("���ͳɹ�");
					return true;
				}else {
					logger.info("����ʧ��");
					return false;

				}

		} catch (Exception e) {
			logger.error("����ͼ����Ϣ����", e);
			return false;
		}
	}

	/*
	 * ��ָ����e���û�������Ϣ���ı���ͼ�ģ�
	 * @param Type:rtfContentΪƴ�Ӻõ�xml�ַ���
	 *
	 * @result 1/0
	 */
	public static boolean sendRtfByHf500(String rtfContent){
		//rtfContent
		anaylsisXmlUtil xml=new anaylsisXmlUtil();//
		System.out.println("����xml......");
		String returnstr = null;// ���ؽ�����ַ���?
		try {
			String apiUrl= SystemConfigUtil.apiUrl;
			    String url = apiUrl+"IMServiceServer/servlet/ThirdPartyServlet?Channel=IM&fCode=HF005&xmlString="+URLEncoder.encode(rtfContent.toString(), "GBK");
			    logger.info("������Ϣurl"+url);
				HttpGet httpget = new HttpGet(url);
				HttpClient client = new DefaultHttpClient();
				HttpResponse result = client.execute(httpget);
				byte[] returnByte=IOUtils.toByteArray(result.getEntity().getContent());
				returnstr=new String(returnByte,"GBK");//���ͺͷ��ص�xml���Ķ���Ҫ����gbk�Ĵ���
				logger.info("return:" + returnstr);
			    if (returnstr == null || returnstr.equals("")) {
				  return false;
			    }
			    returnstr=URLDecoder.decode(returnstr,"GBK");
			    Map<Object,Object> resultMap=new HashMap<Object,Object>();
			    resultMap=xml.getXml(returnstr);

				String err=String.valueOf(resultMap.get("RETCODE"));
				if(err.equals("0")) {
					logger.info("���ͳɹ�");
					return true;
				}else {
					logger.info("����ʧ��");
					return false;

				}

		} catch (Exception e) {
			logger.error("����ͼ����Ϣ����", e);
			return false;
		}
	}




    public static void main(String[] args) {
        System.out.println(DateUtil.format(DateUtil.date(), "HHmmss"));
    }



}
