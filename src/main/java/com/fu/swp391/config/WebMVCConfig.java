package com.fu.swp391.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Properties;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Bean
    public ClassLoaderTemplateResolver CustomTemplateResolver() {
        ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
//        configurer.setPrefix("templates/");
        configurer.setSuffix(".html");
        configurer.setTemplateMode(TemplateMode.HTML);
        configurer.setCharacterEncoding("UTF-8");
        configurer.setOrder(0);  // this is important. This way spring //boot will listen to both places 0 and 1
        configurer.setCheckExistence(true);
        return configurer;
    }

//    @Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver
//    createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r =
//                new SimpleMappingExceptionResolver();
//
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");
//        mappings.setProperty("InvalidCreditCardException", "creditCardError");
//        mappings.setProperty("InvalidCreditCardException", "creditCardError");
//
//        mappings.setProperty("Exception", "404Page/404");
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("404Page/404");    // No default
//        r.setExceptionAttribute("Exception");     // Default is "exception"
//        r.setWarnLogCategory("Some thing wrong");     // No default
//        return r;
//    }
}
