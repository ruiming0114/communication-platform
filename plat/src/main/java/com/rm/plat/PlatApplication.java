package com.rm.plat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication
public class PlatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatApplication.class, args);
    }

}
