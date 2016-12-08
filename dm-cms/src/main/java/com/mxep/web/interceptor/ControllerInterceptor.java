package com.mxep.web.interceptor;

import com.mxep.web.context.ParameterCache;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ranfi on 4/19/16.
 */
public class ControllerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("projectName", ParameterCache.getSystemProp("project.name"));
        session.setAttribute("projectFoot", ParameterCache.getSystemProp("project.foot"));
        session.setAttribute("projectDescription", ParameterCache.getSystemProp("project.description"));
        session.setAttribute("projectKeywords", ParameterCache.getSystemProp("project.keywords"));
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
