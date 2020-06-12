package com.rm.plat.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.rm.plat.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
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
        filterChainDefinitionMap.put("/toAdminister_Book","perms[admin]");
        filterChainDefinitionMap.put("/toAdminister_Video","perms[admin]");
        filterChainDefinitionMap.put("/toAdminister_Topic","perms[admin]");
        filterChainDefinitionMap.put("/toAdminister_Group","perms[admin]");
        filterChainDefinitionMap.put("/toAdminister_User","perms[admin]");
        filterChainDefinitionMap.put("/toAddBook","perms[admin]");
        filterChainDefinitionMap.put("/toEditBook","perms[admin]");
        filterChainDefinitionMap.put("/toAddVideo","perms[admin]");
        filterChainDefinitionMap.put("/toEditVideo","perms[admin]");
        filterChainDefinitionMap.put("/toAdminister_Report","perms[admin]");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);



        //设置登录请求
        bean.setLoginUrl("/toLogin");
        bean.setSuccessUrl("redirect:/index");

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
    }

    //整合shiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
