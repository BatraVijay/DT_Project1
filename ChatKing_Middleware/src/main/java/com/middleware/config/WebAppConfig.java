package com.middleware.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//spring-servlet.xml
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.backend","com.middleware"})
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		CommonsMultipartResolver commonMultipartResolver=new CommonsMultipartResolver();
		return commonMultipartResolver;
}
	public WebAppConfig(){
		System.out.println("Web App Config is instantiated");
	}
	
	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        // Turn off suffix-based content negotiation
        configurer.favorPathExtension(false);
    }
	
}
