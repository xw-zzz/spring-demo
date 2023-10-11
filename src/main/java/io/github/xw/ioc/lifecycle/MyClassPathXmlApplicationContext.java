package io.github.xw.ioc.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("设置beanName");
        super.setBeanName(name);
    }

    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {
        System.out.println("设置环境变量");
        super.setEnvironment(environment);
    }



}
