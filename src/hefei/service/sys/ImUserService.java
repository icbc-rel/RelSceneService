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
 * 获取融e联用户详情信息
 */
public class ImUserService {
	private static final Logger logger = Logger.getLogger(ImUserService.class);

	/*
	 * 根据融e联Id拉取用户信息
	 */
	public static UserDetailInfo FetchUserInfo(String imUserId) {
		UserDetailInfo userDetail = new UserDetailInfo();
		userDetail=FetchUserDetail(1,imUserId);
		return userDetail;
	}

	/*
	 * 根据手机号拉取用户信息
	 */
	public static UserDetailInfo FetchUserInfoByMobileNo(String mobileno) {
		UserDetailInfo userDetail = new UserDetailInfo();
		userDetail=FetchUserDetail(2,mobileno);
		return userDetail;
	}


	/*
	 * 获取用户信息 return:昵称、省城市、注册时间、星级、手机号、是否实名等信息
	 * 1.请求数据中不要有多余的空格。
	 * 2.要求使用字符集UTF-8。
	 * 3.注意请求地址以http开头，而不是https。
	 * 4.请求数据的值要做URL编码处理，否则对于一些特殊字符可能会在URL解码时发生错误。
	 * 5.返回的数据要做URL解码处理。
	 * 6.QueryFlag=1 value需传入融e联Id,QueryFlag=2 value 需传入手机号
	 */
	private static UserDetailInfo FetchUserDetail(int QueryFlag,String value) {
		UserDetailInfo userDetail = new UserDetailInfo();

		String returnstr = null;// 返回结果的字符串
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

			logger.info("获取用户信息url" + url);
			HttpGet httpget = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse result = client.execute(httpget);
			byte[] returnByte = IOUtils.toByteArray(result.getEntity().getContent());
			returnstr = new String(returnByte, "utf-8");
			logger.info("return:" + returnstr);
			//return:{"PRIVATE":{"ICBCUserid":"A20230620000249075","mobileno":"18100000014","userid":"1037667612","sex":"-1","register_time":"2023-06-30 19:22:21","city":"","nickname":"马小花","cisno":"020078999966472","province":"","unino":"","starLevel":"0","customerType":"1","portrait":"https://im.icbc.com.cn/ICBCMPServer/servlet/ICBCGetAvailableImgPathServlet?dir=userinfo/1037667612_poc.jpg"},"PUBLIC":{"errcode":"0","errmsg":""}}
			if (returnstr == null || returnstr.equals("")) {
				return null;
			}
			returnstr = URLDecoder.decode(returnstr, "utf-8");
			JSONObject jsonret = JSONObject.parseObject(returnstr);
			JSONObject err = jsonret.getJSONObject("PUBLIC");
			JSONObject data = jsonret.getJSONObject("PRIVATE");
			// errcode返回值0表示成功，其余均为失败
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
				userDetail.setPortrait(data.getString("portrait"));//头像
				logger.info(JSON.toJSON(userDetail));
				return userDetail;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("拉取用户信息报错", e);
			return null;
		}
	}


}
