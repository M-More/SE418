package com.baowei.wordladder;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("user");
        if (user == null || user.equals(""))  {
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}
