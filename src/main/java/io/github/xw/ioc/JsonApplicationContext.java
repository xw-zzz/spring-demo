package io.github.xw.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author yangliu@tiduyun.com
 * @date 2023/10/10
 */
public class JsonApplicationContext extends AbstractRefreshableConfigApplicationContext {
    public JsonApplicationContext(String path) {
        this.setConfigLocations(path);
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        JsonBeanDefinitionReader beanDefinitionReader = new JsonBeanDefinitionReader(beanFactory);

        // Configure the bean definition reader with this context's
        // resource loading environment.
        beanDefinitionReader.setEnvironment(this.getEnvironment());
        beanDefinitionReader.setResourceLoader(this);
        //beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

        loadBeanDefinitions(beanDefinitionReader);
    }

    protected void loadBeanDefinitions(JsonBeanDefinitionReader reader) throws BeansException, IOException {

        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            reader.loadBeanDefinitions(configLocations);
        }
    }
}
