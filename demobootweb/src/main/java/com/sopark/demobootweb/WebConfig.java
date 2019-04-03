package com.sopark.demobootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi")
                .order(-1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }

    // 추가적으로 message convert 를 추가 함. (대부분 message convert 를 제공함)
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /*
    다른 메시지 컨버터는 사용못함.
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }*/


}
