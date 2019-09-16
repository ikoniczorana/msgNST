package com.zorana;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zorana")
public class WebMvcTestConfig {
	  
	public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	    }
	    
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    }
	
}
