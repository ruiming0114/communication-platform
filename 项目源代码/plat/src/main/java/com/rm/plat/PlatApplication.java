package com.rm.plat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class PlatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.ofBytes(50000000));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofBytes(50000000));
        return factory.createMultipartConfig();
    }
}
