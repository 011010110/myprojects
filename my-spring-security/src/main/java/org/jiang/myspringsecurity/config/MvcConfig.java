package org.jiang.myspringsecurity.config;

import org.jiang.myspringsecurity.mvc.DefaultIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("html", MediaType.TEXT_HTML);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        DefaultIntercepter defaultIntercepter = new DefaultIntercepter();
        registry.addInterceptor(defaultIntercepter)
                .addPathPatterns("/");
    }
}
