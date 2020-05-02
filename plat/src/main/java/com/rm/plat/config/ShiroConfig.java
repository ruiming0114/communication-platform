package com.rm.plat.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加内置过滤器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //授权
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);



        //设置登录请求
        bean.setLoginUrl("/toLogin");
        bean.setSuccessUrl("/index");

        //未授权请求
        bean.setUnauthorizedUrl("/noauth");



        return bean;
    }

    @Bean(name = "securityManager" )
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
//        UserRealm userRealm = new UserRealm();
//        //使用HashedCredentialsMatcher带加密的匹配器来替换原先明文密码匹配器
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //指定加密算法
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        //指定加密次数
//        hashedCredentialsMatcher.setHashIterations(1024);
//        // 生成16进制, 与注册时的生成格式相同
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
//        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//        return userRealm;
    }

    //整合shiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
