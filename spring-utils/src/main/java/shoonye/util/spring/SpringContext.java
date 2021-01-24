package shoonye.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
@Component("currentSpringContext")
public class SpringContext implements ApplicationContextAware{
	private static ApplicationContext ctx = null;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx=applicationContext;
	}
	
	public static ApplicationContext get(){
		return ctx;
	}

	public static<T> T getBean(String name, Class<T> beanClass){
		return ctx.getBean(name, beanClass);
	}
}
