package com.ahold.allwaysdelivery.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author TMGSOFTWARE
 *
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(ApplicationContextProvider.class);

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextProvider.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getNotRequiredBean(Class<T> beanClass) {
		try {
			return getApplicationContext().getBean(beanClass);
		} catch (BeansException e) {
			log.warn(e.getMessage());
		}
		return null;
	}

	public static <T> T getNotRequiredBean(String name, Class<T> beanClass) {
		try {
			return getApplicationContext().getBean(name, beanClass);
		} catch (BeansException e) {
			log.warn(e.getMessage());
		}
		return null;
	}
}
