package com.icbc.rel.hefei.service.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.rel.hefei.entity.UserDetailInfo;
import com.icbc.rel.hefei.util.SystemConfigUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/*
 * ��ȡ��e���û�������Ϣ
 */
public class ImUserService {
	private static final Logger logger = Logger.getLogger(ImUserService.class);

	/*
	 * ������e��Id��ȡ�û���Ϣ
	 */
	public static UserDetailInfo FetchUserInfo(String imUserId) {
		UserDetailInfo userDetail = new UserDetailInfo();
		userDetail=FetchUserDetail(1,imUserId);
		return userDetail;
	}

	/*
	 * �����ֻ�����ȡ�û���Ϣ
	 */
	public static UserDetailInfo FetchUserInfoByMobileNo(String mobileno) {
		UserDetailInfo userDetail = new UserDetailInfo();
		userDetail=FetchUserDetail(2,mobileno);
		return userDetail;
	}


	/*
	 * ��ȡ�û���Ϣ return:�ǳơ�ʡ���С�ע��ʱ�䡢�Ǽ����ֻ��š��Ƿ�ʵ������Ϣ
	 * 1.���������в�Ҫ�ж���Ŀո�
	 * 2.Ҫ��ʹ���ַ���UTF-8��
	 * 3.ע�������ַ��http��ͷ��������https��
	 * 4.�������ݵ�ֵҪ��URL���봦���������һЩ�����ַ����ܻ���URL����ʱ��������
	 * 5.���ص�����Ҫ��URL���봦��
	 * 6.QueryFlag=1 value�贫����e��Id,QueryFlag=2 value �贫���ֻ���
	 */
	private static UserDetailInfo FetchUserDetail(int QueryFlag,String value) {
		UserDetailInfo userDetail = new UserDetailInfo();

		String returnstr = null;// ���ؽ�����ַ���
		try {

			String apiUrl = SystemConfigUtil.apiUrl;
			String url = apiUrl;
			if(QueryFlag==1) {
				url+="IMServiceServer/servlet/ThirdPartyServlet?Channel=IM&fCode=HF001&QueryFlag=1&userid="
						+ URLEncoder.encode(value, "utf-8");
			}else {
				url+="IMServiceServer/servlet/ThirdPartyServlet?Channel=IM&fCode=HF001&QueryFlag=2&mobileno="
						+ URLEncoder.encode(value, "utf-8");
			}

			logger.info("��ȡ�û���Ϣurl" + url);
			HttpGet httpget = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse result = client.execute(httpget);
			byte[] returnByte = IOUtils.toByteArray(result.getEntity().getContent());
			returnstr = new String(returnByte, "utf-8");
			logger.info("return:" + returnstr);
			//return:{"PRIVATE":{"ICBCUserid":"A20230620000249075","mobileno":"18100000014","userid":"1037667612","sex":"-1","register_time":"2023-06-30 19:22:21","city":"","nickname":"��С��","cisno":"020078999966472","province":"","unino":"","starLevel":"0","customerType":"1","portrait":"https://im.icbc.com.cn/ICBCMPServer/servlet/ICBCGetAvailableImgPathServlet?dir=userinfo/1037667612_poc.jpg"},"PUBLIC":{"errcode":"0","errmsg":""}}
			if (returnstr == null || returnstr.equals("")) {
				return null;
			}
			returnstr = URLDecoder.decode(returnstr, "utf-8");
			JSONObject jsonret = JSONObject.parseObject(returnstr);
			JSONObject err = jsonret.getJSONObject("PUBLIC");
			JSONObject data = jsonret.getJSONObject("PRIVATE");
			// errcode����ֵ0��ʾ�ɹ��������Ϊʧ��
			if (err.getString("errcode").trim().equals("0")) {
				userDetail.setCisno(data.getString("cisno"));
				userDetail.setCity(data.getString("city"));
				userDetail.setCreateTime(new Date());
				userDetail.setIcbcUserId(data.getString("ICBCUserid"));
				userDetail.setImUserId(data.getString("userid"));
				userDetail.setMobileNo(data.getString("mobileno"));
				userDetail.setMpId("");
				userDetail.setNickName(data.getString("nickname"));
				userDetail.setCustomerType(data.getString("customerType"));
				userDetail.setProvince(data.getString("province"));
				userDetail.setRegisterTime(data.getString("cisno"));
				userDetail.setSex(data.getString("sex"));
				userDetail.setStarLevel(data.getString("starLevel"));
				userDetail.setUnino(data.getString("unino"));
				userDetail.setPortrait(data.getString("portrait"));//ͷ��
				logger.info(JSON.toJSON(userDetail));
				return userDetail;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("��ȡ�û���Ϣ����", e);
			return null;
		}
	}


}
