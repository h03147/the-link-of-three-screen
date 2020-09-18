package com.cmkj.the_link_of_threescreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class TheLinkOfThreescreenApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.print("视频上传项目启动类2.==="+"\n");
        return application.sources(TheLinkOfThreescreenApplication.class);
    }

    public static void main(String[] args) {
        System.out.print("视频上传项目启动类1.==="+"\n");
        SpringApplication.run(TheLinkOfThreescreenApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

}
