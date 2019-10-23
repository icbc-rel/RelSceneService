package com.icbc.rel.hefei.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/*
 * ��¼session����ز���
 */
public class SessionUtil {
	private static final Logger logger = Logger.getLogger(SessionUtil.class);

	private static final String adminId = "adminId";// ƽ̨����Աid
	private static final String adminName = "adminName";// ƽ̨����Ա����
	private static final String loginType = "loginType";// 1-ƽ̨����Ա 2-���ںŹ���Ա
	private static final String mpId = "mpId";// ���ں�id
	private static final String mpName = "mpName";// ���ں�����
	private static final String imUserId = "imUserId";// ��˿id
	private static final String customerType = "customerType";// �û�ʵ����־
	private static final String nickname = "nickname";// �û��ǳ�
	private static final String mobileno = "mobileno";// �û��ֻ���

	/*
	 * ����ƽ̨����Ա�û�����id��session
	 */
	public static boolean setAdminSession(HttpSession session, String id, String name) {
		try {
			session.setAttribute(adminId, id);
			session.setAttribute(adminName, name);
			session.setAttribute(loginType, 1);
		} catch (Exception e) {
			logger.error("��¼ϵͳ�󱣴��û�id��sessionʧ��", e);
			return false;
		}
		return true;
	}

	/*
	 * ���湫�ںŹ���Ա�û�����id��session
	 */
	public static boolean setMpSession(HttpSession session, String id, String name) {
		try {
			session.setAttribute(mpId, id);
			session.setAttribute(mpName, name);
			session.setAttribute(loginType, 2);
		} catch (Exception e) {
			logger.error("��תϵͳ�󱣴��û�id��sessionʧ��", e);
			return false;
		}
		return true;
	}

	/*
	 * ��ȡsession�е�AdminId
	 */
	public static String getAdminId(HttpSession session) {
		String id = "";
		try {
			id = (String) session.getAttribute(adminId);
		} catch (Exception ex) {
			logger.error("session��ȡid����", ex);
			id = "";
		}
		return id;
	}

	/*
	 * ��ȡsession�е�AdminName
	 */
	public static String getAdminName(HttpSession session) {
		String name = "";
		try {
			name = (String) session.getAttribute(adminName);
		} catch (Exception ex) {
			logger.error("session��ȡname����", ex);
			name = "";
		}
		return name;
	}

	/*
	 * ��ȡsession�е�mpId
	 */
	public static String getMpId(HttpSession session) {
		String id = "";
		try {
			id = (String) session.getAttribute(mpId);
		} catch (Exception ex) {
			logger.error("session��ȡid����", ex);
			id = "";
		}
		return id;
	}

	/*
	 * ��ȡsession�е�mpName
	 */
	public static String getMpName(HttpSession session) {
		String name = "";
		try {
			name = (String) session.getAttribute(mpName);
		} catch (Exception ex) {
			logger.error("session��ȡname����", ex);
			name = "";
		}
		return name;
	}

	/*
	 * ����ImUserId
	 */
	public static boolean setImUserId(HttpSession session, String ImUserId) {
		try {
			session.setAttribute(imUserId, ImUserId);
		} catch (Exception e) {
			logger.error("����ImUserId��sessionʧ��", e);
			return false;
		}
		return true;
	}

	/*
	 * ��ȡImUserId
	 */
	public static String getImUserId(HttpSession session) {

		String relid = "123";
		try {
			relid = (String) session.getAttribute(imUserId);
		} catch (Exception ex) {
			logger.error("session��ȡname����", ex);

		}
		if (relid == null) {
			relid = "123";
		}
		return relid;
	}

	/*
	 * �����û�ʵ����־���ǳơ��ֻ���
	 */
	public static boolean setUserInfo(HttpSession session, String customertype, String nickName, String mobileNo) {
		try {
			session.setAttribute(customerType, customertype);
			session.setAttribute(nickname, nickName);
			session.setAttribute(mobileno, mobileNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("�����û���Ϣ��sessionʧ��", e);
			return false;
		}
		return true;
	}

	/*
	 * ��ȡsession�е��û�ʵ����־
	 */
	public static String getCustomerType(HttpSession session) {
		String customertype = "";
		try {
			customertype = (String) session.getAttribute(customerType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session��ȡ�û�ʵ����־����", e);
			customertype = "";
		}
		return customertype;
	}

	/*
	 * ��ȡsession�е��û��ǳ�
	 */
	public static String getNickName(HttpSession session) {
		String nickName = "";
		try {
			nickName = (String) session.getAttribute(nickname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session��ȡ�û�ʵ����־����", e);
			nickName = "";
		}
		return nickName;
	}

	/*
	 * ��ȡsession�е��û��ֻ���
	 */
	public static String getMobileNo(HttpSession session) {
		String mobileNo = "";
		try {
			mobileNo = (String) session.getAttribute(mobileno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("session��ȡ�û�ʵ����־����", e);
			mobileNo = "";
		}
		return mobileNo;
	}
}
