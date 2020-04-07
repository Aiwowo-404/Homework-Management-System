package com.axw.Configuration;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/student")){
            if(request.getRequestURI().contains("/goLoginStudent")){
                return true;
            }else{
                if(request.getSession().getAttribute(request.getSession().getId()+"student")!=null){
                    return true;
                }else{
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }
        else if (request.getRequestURI().contains("/teacher")){
            if(request.getRequestURI().contains("/goLoginTeacher")){
                return true;
            }else{
                if(request.getSession().getAttribute(request.getSession().getId()+"teacher")!=null){
                    return true;
                }else{
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }
        else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
