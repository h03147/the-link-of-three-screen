package com.cmkj.the_link_of_threescreen.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 在配置文件中配置的文件保存路径
     */

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.parse("150MB"));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("150MB"));
        return factory.createMultipartConfig();
    }

    // 配置允许通过外部url访问指定的服务器路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")){
            String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
            System.out.print("1.上传配置类imagesPath=="+imagesPath+"\n");
            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/btn1files/";
            mImagesPath = imagesPath;
        }
        System.out.print("imagesPath============="+mImagesPath+"\n");
        //LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath============="+mImagesPath+"\n");
        registry.addResourceHandler("/btn1files/**").addResourceLocations(mImagesPath);
        // TODO Auto-generated method stub
        System.out.print("2.上传配置类mImagesPath=="+mImagesPath+"\n");
        super.addResourceHandlers(registry);
    }
}
