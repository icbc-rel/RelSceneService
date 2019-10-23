package com.icbc.rel.hefei.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    /*
	 * ������Դ��������������ȡ��cookies�����ж�cookis����ز���
	 *
	 */

	public class CookiesUtil  {
		private static String openid="Openid";

		public static String getOpenId(HttpServletRequest request)
		{
			Cookie openidCook = CookiesUtil.getCookieByName(request, openid);
			String openid = null;
			if (openidCook != null) {
				openid = openidCook.getValue();
			}
			if (openid == null || openid.length() == 0) {
				openid = "123456789";
			}
			return openid;
		}

		public static void SaveOpenId(HttpServletResponse response,String openid)
		{
			if (openid != null) {
				CookiesUtil.setCookie(response, "Openid", openid, 3600);
			}
		}


	    /**
	     * �������ֻ�ȡcookie
	     *
	     * @param request
	     * @param name
	     * cookie����
	     * @return
	     */
	    public static Cookie getCookieByName(HttpServletRequest request, String name) {
	        Map<String, Cookie> cookieMap = ReadCookieMap(request);
	        if (cookieMap.containsKey(name)) {
	            Cookie cookie = (Cookie) cookieMap.get(name);
	            return cookie;
	        } else {
	            return null;
	        }
	    }

	    /**
	     * ��cookie��װ��Map����
	     *
	     * @param request
	     * @return
	     */
	    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
	        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	        Cookie[] cookies = request.getCookies();
	        if (null != cookies) {
	            for (Cookie cookie : cookies) {
	                cookieMap.put(cookie.getName(), cookie);
	            }
	        }
	        return cookieMap;
	    }

	    /**
	     * ����Cookies
	     *
	     * @param response
	     *            servlet����
	     * @param value
	     *            ����ֵ
	     * @author jxf
	     */
	    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value,int time) {
	        // newһ��Cookie����,��ֵ��Ϊ����
	        Cookie cookie = new Cookie(name, value);
	        // tomcat�¶�Ӧ�ù���
	        cookie.setPath("/");
	        // ���cookie��ֵ�к�������ʱ����Ҫ��cookie���б��룬��Ȼ���������
	        try {
	            URLEncoder.encode(value, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        cookie.setMaxAge(time);
	        // ��Cookie��ӵ�Response��,ʹ֮��Ч
	        response.addCookie(cookie); // addCookie������Ѿ�������ͬ���ֵ�cookie�������µĸ��Ǿɵ�cookie
	        return response;
	    }

}
