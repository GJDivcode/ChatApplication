package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger=LoggerFactory.getLogger(AppIntializer.class);
		
	@Override
	protected Class[] getRootConfigClasses() {
		logger.debug("starting of the method getRootConfigclasses");
	    return new Class[]{AppConfig.class  };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("starting of the method getservletConfigclasses");
		return new Class[]{AppConfig.class  };
	}

	@Override
	protected String[] getServletMappings() {
		logger.debug("starting of the method getservletConfigclasses");
		return new String[]{"/"};
	}
	

}
