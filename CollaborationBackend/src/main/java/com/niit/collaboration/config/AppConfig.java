package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/*import org.springframework.web.servlet.view.JstlView;*/
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableWebMvc

public class AppConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger=LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public InternalResourceViewResolver  getInternalResourceViewResolver(){
		logger.debug("starting of the method Viewresolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		logger.debug("Ending of the method Viewresolver");
	    return resolver;
		
	}
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	registry.addResourceHandler("/app-resources/**").addResourceLocations("/resources/");
	    }    
	
	
	
	/*@Bean
	 public UrlBasedViewResolver getViewResovler() {
	        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
	        urlBasedViewResolver.setViewClass(JstlView.class);
	        urlBasedViewResolver.setPrefix("/WEB-INF/view/");
	        urlBasedViewResolver.setSuffix(".html");
	        return urlBasedViewResolver;
	    }*/
}
