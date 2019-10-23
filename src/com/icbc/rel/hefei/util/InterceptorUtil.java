package com.icbc.rel.hefei.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * ������ ������IP����
 */
public class InterceptorUtil extends HandlerInterceptorAdapter{


    /**
     * 1.����Controller����֮ǰִ��
     * @param request
     * @param response
     * @param handler
     * @return ����ֵΪtrueʱ�ɼ���ִ��Controller��preHandle()��afterCompletion()��Ϊfalseʱִֹͣ���κη�����
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url=request.getRequestURL().toString();
        //String ip=request.getRemoteAddr();
        String ip=IpAdressUtil.getip(request);
        System.out.println(ip+"���ڷ���"+url);
        //ip����
        boolean flag=IplimitUtil.requestFrequencyCheck(ip,url);
        if(!flag) {
        	System.out.println(ip+"���ڿ��ɶ��⹥����Ϊ");
        	return false;
        }

        //��֤�û��Ƿ��¼��û�е�¼����ת����¼����
        if(url.contains("/ad/") &&  !url.endsWith("myscene") && !url.endsWith("checkUser")) {
        	String loginId=SessionUtil.getAdminId(request.getSession());
        	if(loginId==null || loginId.equals("")) {
        		response.sendRedirect("../login");
        		return false;
        	}else {
        		return true;
        	}
        }
        if(url.contains("/mp/") &&  !url.endsWith("myscene")) {
        	String mpid=SessionUtil.getMpId(request.getSession());
        	if(mpid==null || mpid.equals("")) {
        		return false;
        	}else {
        		return true;
        	}
        }
        return true;
    }

    /**
     * 2.Controller����ִ����ϵ���δ������ͼ��Ⱦʱִ��
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 3.��ͼ��Ⱦ��ɣ��������������ִ��
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
