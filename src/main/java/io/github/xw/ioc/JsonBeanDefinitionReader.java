package io.github.xw.ioc;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangliu@tiduyun.com
 * @date 2023/10/10
 */
public class JsonBeanDefinitionReader extends AbstractBeanDefinitionReader {
    /**
     * Create a new AbstractBeanDefinitionReader for the given bean factory.
     * <p>
     * If the passed-in bean factory does not only implement the BeanDefinitionRegistry interface but also the
     * ResourceLoader interface, it will be used as default ResourceLoader as well. This will usually be the case for
     * {@link ApplicationContext} implementations.
     * <p>
     * If given a plain BeanDefinitionRegistry, the default ResourceLoader will be a
     * {@link PathMatchingResourcePatternResolver}.
     * <p>
     * If the passed-in bean factory also implements {@link EnvironmentCapable} its environment will be used by this
     * reader. Otherwise, the reader will initialize and use a {@link StandardEnvironment}. All ApplicationContext
     * implementations are EnvironmentCapable, while normal BeanFactory implementations are not.
     *
     * @param registry
     *            the BeanFactory to load bean definitions into, in the form of a BeanDefinitionRegistry
     * @see #setResourceLoader
     * @see #setEnvironment
     */
    protected JsonBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {

        try (InputStream inputStream = resource.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String content = new String(bytes);
            JsonObjectDefine jsonObjectDefine = JSON.parseObject(content, JsonObjectDefine.class);

            AbstractBeanDefinition bd = new GenericBeanDefinition();
            bd.setResource(resource);
            bd.setDescription("");
            bd.setBeanClassName(jsonObjectDefine.getClassName());

            jsonObjectDefine.getProperties().forEach((k, v) -> {
                PropertyValue pv = new PropertyValue(k, v);
                bd.getPropertyValues().addPropertyValue(pv);
            });

            BeanDefinitionHolder beanDefinitionHolder =
                new BeanDefinitionHolder(bd, jsonObjectDefine.getId(), null);
            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, getRegistry());

            return 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
