package io.github.xw.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * @author xw
 * @date 2023/10/9
 */
public class MyBean implements EmbeddedValueResolverAware, InitializingBean {

    private StringValueResolver embeddedValueResolver;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("EmbeddedValueResolverAware.setEmbeddedValueResolver方法");
        this.embeddedValueResolver=resolver;
        String resolvedValue = embeddedValueResolver.resolveStringValue("${my.property}");

    }

    public void customMethodInit(){
        System.out.println("自定义方法调用");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet");
        System.out.println(this.message);
    }
}
