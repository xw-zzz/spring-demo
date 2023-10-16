package io.github.xw.aop;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ProxyFactoryBeanDemo {

    @Configuration
    public static class Config {

        @Bean
        public MethodBeforeAdvice beforeAdvice() {


            return new MethodBeforeAdvice() {
                @Override
                public void before(Method method, Object[] args, Object target) throws Throwable {
                    System.out.println("before");
                }
            };
        }

        @Bean
        public Advisor advisor() {
            NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor
                    = new NameMatchMethodPointcutAdvisor(beforeAdvice());
            nameMatchMethodPointcutAdvisor.setMappedName("h*");
            return nameMatchMethodPointcutAdvisor;
        }

        @Bean
        public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
            ProxyFactoryBean factoryBean = new ProxyFactoryBean();
            // 指定代理接口
            factoryBean.setProxyInterfaces(new Class[]{ Hello.class });
            // 指定 Advisor 的 beanName
            factoryBean.setInterceptorNames("advisor");
            // 指定 target
            factoryBean.setTarget(new HelloImpl());

            return factoryBean;
        }

    }

    public interface Hello {
        void hello();
    }

    public static class HelloImpl implements Hello {

        @Override
        public void hello() {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);
        // 获取代理对象
        Hello bean = context.getBean(Hello.class);
        bean.hello();
    }
}
