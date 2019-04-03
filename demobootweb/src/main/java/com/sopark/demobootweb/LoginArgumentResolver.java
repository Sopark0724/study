package com.sopark.demobootweb;

import org.springframework.core.MethodParameter;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    // 지원 타입
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() != null && parameter.getParameterType().equals(Login.class);
    }

    // 변환할 값
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return new Login(request.getParameter("username"), request.getParameter("password"));
    }
}
