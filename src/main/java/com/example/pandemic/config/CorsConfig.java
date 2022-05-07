package com.example.pandemic.config;

import org.springframework.web.cors.CorsConfiguration;

/**
 * @author codejie
 * @since 2022-04-28
 */
/*
* 配置跨域
* */
public class CorsConfig {
//    当前跨域请求最大有效期,默认一天
    private static final long MAX_TIME=24*60*60;

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问源请求头
        corsConfiguration.addAllowedMethod("*");//设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_TIME);//
        return corsConfiguration;
    }
}
