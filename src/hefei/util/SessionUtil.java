package com.icbc.rel.hefei.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

/*
 * ???session????????
 */
public class SessionUtil {
	private static final Logger logger = Logger.getLogger(SessionUtil.class);

	private static final String adminId = "adminId";// ???????id
	private static final String adminName = "adminName";// ???????????
	private static final String loginType = "loginType";// 1-??????? 2-?????????
	private static final String mpId = "mpId";// ?????id
	private static final String mpName = "mpName";// ?????????
	private static final String imUserId = "imUserId";// ???id
	private static final String customerType = "customerType";// ?????????
	private static final String nickname = "nickname";// ??????
	private static final String mobileno = "mobileno";// ????????

	/*
	 * ??????????????????id??session
	 */
	public static boolean setAdminSession(HttpSession session, String id, String name) {
		try {
			session.setAttribute(adminId, id);
			session.setAttribute(adminName, name);
			session.setAttribute(loginType, 1);
		} catch (Exception e) {
			logger.error("???????????id??session???", e);
			return false;
		}
		return true;
	}

	/*
	 * ???湫??????????????id??session
	 */
	public static boolean setMpSession(HttpSession session, String id, String name) {
		try {
			session.setAttribute(mpId, id);
			session.setAttribute(mpName, name);
			session.setAttribute(loginType, 2);
		} catch (Exception e) {
			logger.error("???????????id??session???", e);
			return false;
		}
		return true;
	}

	/*
	 * ???session?е?AdminId
	 */
	public static String getAdminId(HttpSession session) {
		String id = "";
		try {
			id = (String) session.getAttribute(adminId);
		} catch (Exception ex) {
			logger.error("session???id????", ex);
			id = "";
		}
		return id;
	}

	/*
	 * ???session?е?AdminName
	 */
	public static String getAdminName(HttpSession session) {
		String name = "";
		try {
			name = (String) session.getAttribute(adminName);
		} catch (Exception ex) {
			logger.error("session???name????", ex);
			name = "";
		}
		return name;
	}

	/*
	 * ???session?е?mpId
	 */
	public static String getMpId(HttpSession session) {
		String id = "";
		try {
			id = (String) session.getAttribute(mpId);
		} catch (Exception ex) {
			logger.error("session???id????", ex);
			id = "";
		}
		return id;
	}

	/*
	 * ???session?е?mpName
	 */
	public static String getMpName(HttpSession session) {
		String name = "";
		try {
			name = (String) session.getAttribute(mpName);
		} catch (Exception ex) {
			logger.error("session???name????", ex);
			name = "";
		}
		return name;
	}

	/*
	 * ????ImUserId
	 */
	public static boolean setImUserId(HttpSession session, String ImUserId) {
		try {
			session.setAttribute(imUserId, ImUserId);
		} catch (Exception e) {
			logger.error("????ImUserId??session???", e);
			return false;
		}
		return true;
	}

	/*
	 * ???ImUserId
	 */
	public static String getImUserId(HttpSession session) {

		String relid = "123";
		try {
			relid = (String) session.getAttribute(imUserId);
		} catch (Exception ex) {
			logger.error("session???name????", ex);

		}
		if (relid == null) {
			relid = "123";
		}
		return relid;
	}

	/*
	 * ????????????????????????
	 */
	public static boolean setUserInfo(HttpSession session, String customertype, String nickName, String mobileNo) {
		try {
			session.setAttribute(customerType, customertype);
			session.setAttribute(nickname, nickName);
			session.setAttribute(mobileno, mobileNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("????????????session???", e);
			return false;
		}
		return true;
	}

	/*
	 * ???session?е??????????
	 */
	public static String getCustomerType(HttpSession session) {
		String customertype = "";
		try {
			customertype = (String) session.getAttribute(customerType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session????????????????", e);
			customertype = "";
		}
		return customertype;
	}

	/*
	 * ???session?е???????
	 */
	public static String getNickName(HttpSession session) {
		String nickName = "";
		try {
			nickName = (String) session.getAttribute(nickname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session????????????????", e);
			nickName = "";
		}
		return nickName;
	}

	/*
	 * ???session?е?????????
	 */
	public static String getMobileNo(HttpSession session) {
		String mobileNo = "";
		try {
			mobileNo = (String) session.getAttribute(mobileno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session????????????????", e);
			mobileNo = "";
		}
		return mobileNo;
	}
}
