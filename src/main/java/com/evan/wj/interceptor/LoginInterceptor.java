package com.evan.wj.interceptor;

import com.evan.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object o) throws Exception{
        HttpSession session = httpServletRequest.getSession();
        // request.getContextPath()，返回路径
        String contextPath = session.getServletContext().getContextPath();
        //需要拦截的路径列表
        String[] requireAuthPages = new String[]{"index"};
        //返回客户端发出请求时的完整URL
        String uri = httpServletRequest.getRequestURI();
        uri = StringUtils.remove(uri,contextPath+"/");
        String page = uri;
        if(beginWith(page,requireAuthPages)){
            User user = (User)session.getAttribute("user");
            //如果session中不存在user属性，则跳转到login页面
            if(user==null){
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否以指定字符串开始
     * @param page
     * @param requireAuthPages
     * @return
     */
    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for(String requireAuthPage:requireAuthPages){
            if(StringUtils.startsWith(page,requireAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }
}
