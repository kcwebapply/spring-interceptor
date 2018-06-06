package com.example.configuration.config;

import com.example.configuration.interceptor.RequestHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class RequestHandlerConfig extends WebMvcConfigurationSupport {


       /**
        * interceptorをbeanに登録
        */
        @Bean
        public RequestHandlerInterceptor requestHandlerInterceptor() {
            return new RequestHandlerInterceptor();
        }
        /**
         * intercptorのbeanをurlリクエストパターンに追加
         */
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(requestHandlerInterceptor()).addPathPatterns("/**");
        }
}
